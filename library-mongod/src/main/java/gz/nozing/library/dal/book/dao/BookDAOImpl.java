/**
 * 
 */
package gz.nozing.library.dal.book.dao;

import gz.nozing.library.common.utils.CommonUtils;
import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.BookDOBlock;
import gz.nozing.library.dal.common.BaseDAOImpl;
import gz.nozing.library.dal.common.annotation.MongoDBUtil;
import gz.nozing.library.dal.common.pagination.PaginationResultDTO;
import gz.nozing.library.dal.common.pagination.PaginationSearchDTO;
import gz.nozing.library.dal.exception.DALException;
import gz.nozing.library.dal.exception.EntityNotFoundException;
import gz.nozing.library.dal.exception.MethodParameterIsEmptyException;
import gz.nozing.library.dal.util.DalUtil;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * <p>
 * DAO que se utilizara para acceder a los datos de libros almacenados en 
 * la base de datos
 * </p>
 * 
 * @author nozing
 *
 */
public class BookDAOImpl extends BaseDAOImpl<BookDO> implements BookDAO {

    private static Logger log = Logger.getLogger(BookDAOImpl.class);

    /**
     * @param database
     */
    public BookDAOImpl() {
	super();
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.book.dao.BookDAO#update(gz.nozing.library.dal.book.obj.BookDO)
     */
    @Override
    public BookDO update(final BookDO book) throws DALException, EntityNotFoundException {

	if (book.getId() == null) {

	    throw new EntityNotFoundException(
		    "Entity 'book' doesn't have an '_id'");
	}
	
	book.setLastModificationDate(new Date());

	Document updatedObj = MongoDBUtil.createDBObj(book);
	updatedObj.remove("_id");
	
	if (book.getAuthors() == null || book.getAuthors().isEmpty()) {
	    
	    updatedObj.append("authors", null);
	}
	
	log.trace("'_id' used for matching: " + book.getId());
	UpdateResult updateResult = this.getDatabase()
		.getCollection(
			MongoDBUtil.getColletionNameFromEntity(BookDO.class))
		.updateOne(
			Filters.eq("_id", new ObjectId(book.getId())), 
			new Document("$set", updatedObj));
	
	if (log.isTraceEnabled()) {
	    log.trace("Documents matched: " + updateResult.getMatchedCount());
//	    log.trace("Documents modified: " + updateResult.getModifiedCount()); // No funciona por culpa de la versión del servidor
	    
	    if (updateResult.getUpsertedId() != null) {
		log.trace("Documents upsertedId: "
			+ updateResult.getUpsertedId().asString());
	    }
	}
	
	return this.findEntityById(book.getId());
    }

    @Override
    public List<BookDO> find(BookDO bookCriteria) throws DALException {

	log.debug("Entering 'find'");
	
	log.trace(String.format("Criteria: %s", bookCriteria.toString())); 
	
	BasicDBObject criteria = new BasicDBObject();
	
	if (bookCriteria.getTitle() != null 
		&& !bookCriteria.getTitle().isEmpty()) {
	    
	    criteria.put("title", bookCriteria.getTitle());
	}
	
	List<AuthorDTO> authors = bookCriteria.getAuthors();
	if (authors != null 
		&& !authors.isEmpty()) {
	    
	    criteria.put("authors", authors);
	}
	
	FindIterable<Document> cursor = 
		this.getDatabase().getCollection(MongoDBUtil.getColletionNameFromEntity(
			BookDO.class)).find(criteria);
	
	final List<BookDO> books = new LinkedList<BookDO>();
	
	/* Rellenamos la lista con el resultado */
	cursor.forEach(new BookDOBlock(books));
	
	return books;
    }

    @Override
    public BookDO findEntityById(String entityId) throws EntityNotFoundException,
    DALException {

	log.debug("Entering 'findEntityById'");
	log.trace("Identifier for search: '" + entityId + "'");

	BasicDBObject objId = new BasicDBObject("_id", new ObjectId(entityId));

	MongoCollection<Document> col = this.getDatabase().getCollection(
		MongoDBUtil.getColletionNameFromEntity(BookDO.class));
	
	FindIterable<Document> cursor = col.find(objId);

	if (cursor.first() == null) {

	    throw new EntityNotFoundException(
		    "No 'book' with id '" + entityId + "'");
	} else {
	    
	    final List<BookDO> books = new LinkedList<BookDO>();
	    cursor.forEach(new BookDOBlock(books));
	    
	    return books.get(0);
	}
    }
    
    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#save(java.lang.Object)
     */
    @Override
    public BookDO save(BookDO entity) throws DALException {

	log.debug("Entering 'save'");

	entity.setCreationDate(new Date());
	entity.setLastModificationDate(new Date());

	Document bookObj = 
		MongoDBUtil.createDBObj(entity);
	try {

	    this.getDatabase().getCollection(
		    MongoDBUtil.getColletionNameFromEntity(
			    entity.getClass())).insertOne(bookObj);

	} catch (Exception e) {

	    throw new DALException("Error inserting 'book'", e);
	}

	entity.setId(bookObj.getObjectId("_id").toHexString());

	return entity;
    }

    @Override
    public void delete(BookDO entity) throws DALException {

	this.delete(entity.getId());
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#delete(java.lang.String)
     */
    @Override
    public void delete(String entityId) throws DALException {

	log.debug("Entering 'delete'");
	if (entityId == null) {

	    throw new MethodParameterIsEmptyException(
		    "Parameter 'entityId' can't be null");
	}

	DeleteResult deleteResult = this.getDatabase().getCollection(
		MongoDBUtil.getColletionNameFromEntity(BookDO.class)).deleteOne(
			new BasicDBObject("_id", new ObjectId(entityId)));
	
	if (deleteResult.getDeletedCount() == 0) {
	    
	    throw new EntityNotFoundException(
		   String.format("Entity '%s' doesn't exist", entityId));
	}
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.book.dao.BookDAO#findBooksPaginated(gz.nozing.library.dal.common.pagination.PaginationSearchDTO)
     */
    @Override
    public PaginationResultDTO<BookDO> findBooksPaginated(
	    PaginationSearchDTO<BookDO> paginationCriteria) throws DALException {
	
	log.debug("Entering 'findBooksPaginated'");
	
	log.trace(String.format("Criteria: %s", paginationCriteria.toString())); 
	
	Document criteria = new Document();
	
	BookDO bookCriteria = paginationCriteria.getSearchObj();
	if (bookCriteria.getTitle() != null 
		&& !bookCriteria.getTitle().isEmpty()) {
	    
	    criteria.put("title", DalUtil.generateCommonRegex(
		    bookCriteria.getTitle()));
	}
	
	if (bookCriteria.getLocation() != null 
		&& !bookCriteria.getLocation().isEmpty()) {
	    
	    criteria.put("location", DalUtil.generateCommonRegex(
		    bookCriteria.getLocation()));
	}
	
	if (bookCriteria.getCategory() != null 
		&& !bookCriteria.getCategory().isEmpty()) {
	    
	    criteria.put("category", DalUtil.generateCommonRegex(
		    bookCriteria.getCategory()));
	}
	
	List<AuthorDTO> authors = bookCriteria.getAuthors();
	if (!CommonUtils.isEmpty(authors)) {
	    
	    for (AuthorDTO author : authors) {
		
		Document authorDoc = new Document();
		if (author.getAuthorId() != null) {
		    
		    authorDoc.put("id", author.getAuthorId());
		}
		
		if (author.getName() != null) {
		    
		    authorDoc.put("name", DalUtil.generateCommonRegex(author.getName()));
		}

		if (author.getSurname() != null) {
		    
		    authorDoc.put("surname", DalUtil.generateCommonRegex(author.getSurname()));
		}
		
		criteria.put("authors", new Document(
			"$elemMatch" , authorDoc));
	    }	   
	} 
	
	log.debug(String.format("Criteria: %s", criteria));
	
	MongoCollection<Document> col = this.getDatabase().getCollection(
		MongoDBUtil.getColletionNameFromEntity(BookDO.class));
	
	/* Buscamos el número total de resultados que cumplen el filtro */
	Long count = col.count(criteria);
	
	Integer initialPosition = 0;
	if ((paginationCriteria.getInitialPosition() - 1) > 0) {
	    
	    initialPosition = (paginationCriteria.getInitialPosition() - 1)
		* paginationCriteria.getPageSize();
	}
	log.debug(String.format("Using 'initialPosition': %s", initialPosition));
	Document sortCriteria = new Document();
	sortCriteria.append("creationDate", 1);
	
	FindIterable<Document> cursor = col.find(criteria).skip(
		initialPosition).sort(sortCriteria).limit(paginationCriteria.getPageSize());
	
	final List<BookDO> books = new LinkedList<BookDO>();
	
	/* Rellenamos la lista con el resultado */
	cursor.forEach(new BookDOBlock(books));
	
	PaginationResultDTO<BookDO> result = new PaginationResultDTO<BookDO>(
		paginationCriteria.getInitialPosition(), paginationCriteria.getPageSize(), 
		count.intValue());
	result.setResult(books);
	
	return result;
    }
}
