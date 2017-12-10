/**
 * 
 */
package gz.nozing.library.dal.author.dao;

import gz.nozing.library.dal.author.AuthorDO;
import gz.nozing.library.dal.author.AuthorDOBlock;
import gz.nozing.library.dal.common.BaseDAOImpl;
import gz.nozing.library.dal.common.annotation.MongoDBUtil;
import gz.nozing.library.dal.common.pagination.PaginationResultDTO;
import gz.nozing.library.dal.common.pagination.PaginationSearchDTO;
import gz.nozing.library.dal.exception.DALException;
import gz.nozing.library.dal.exception.EntityNotFoundException;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

/**
 * @author nozing
 *
 */
public class AuthorDAOImpl extends BaseDAOImpl<AuthorDO> implements AuthorDAO {

    private static Logger log = Logger.getLogger(AuthorDAOImpl.class);

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#update(java.lang.Object)
     */
    @Override
    public AuthorDO update(AuthorDO author) throws DALException {
	
	if (author.getId() == null) {

	    throw new EntityNotFoundException(
		    "Entity 'author' doesn't have an '_id'");
	}
	
	author.setModificationDate(new Date());
	
	Document updatedObj = MongoDBUtil.createDBObj(author);
	updatedObj.remove("_id");
	
	log.trace(String.format("'_id' used for matching: %s", author.getId()));
	UpdateResult updateResult = this.getDatabase()
		.getCollection(
			MongoDBUtil.getColletionNameFromEntity(AuthorDO.class))
		.updateOne(
			Filters.eq("_id", new ObjectId(author.getId())), 
			new Document("$set", updatedObj));
	
	if (log.isTraceEnabled()) {
	    log.trace("Documents matched: " + updateResult.getMatchedCount());
//	    log.trace("Documents modified: " + updateResult.getModifiedCount()); // No funciona por culpa de la versión del servidor
	    
	    if (updateResult.getUpsertedId() != null) {
		log.trace("Documents upsertedId: "
			+ updateResult.getUpsertedId().asString());
	    }
	}
	
	return this.findEntityById(author.getId());
    }

    @Override
    public void delete(AuthorDO entity) throws DALException {

	this.delete(entity.getId());
    }

    @Override
    public void delete(String entityId) throws DALException {
	
	if (entityId == null) {

	    throw new EntityNotFoundException(
		    "Entity doesn't have an '_id'");
	}

	this.getDatabase().getCollection(
		MongoDBUtil.getColletionNameFromEntity(AuthorDO.class)).deleteOne(
			new BasicDBObject("_id", new ObjectId(entityId)));
    }

    @Override
    public AuthorDO findEntityById(String entityId)
	    throws EntityNotFoundException, DALException {
	
	log.debug("Entering 'findEntityById'");
	log.trace("Identifier for search: '" + entityId + "'");

	BasicDBObject objId = new BasicDBObject("_id", new ObjectId(entityId));

	MongoCollection<Document> col = this.getDatabase().getCollection(
		MongoDBUtil.getColletionNameFromEntity(AuthorDO.class));
	
	FindIterable<Document> cursor = col.find(objId);

	if (cursor.first() == null) {

	    throw new EntityNotFoundException(
		   String.format("No 'author' with id '%s'", entityId));
	} else {
	    
	    final List<AuthorDO> authors = new LinkedList<AuthorDO>();
	    cursor.forEach(new AuthorDOBlock(authors));
	    
	    log.debug("Exiting 'findEntityById'");
	    return authors.get(0);
	}
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.common.BaseDAO#save(java.lang.Object)
     */
    @Override
    public AuthorDO save(AuthorDO entity) throws DALException {

	log.debug("Entering 'save'");
	log.trace(entity.toString());
	
	entity.setCreationDate(new Date());
	entity.setModificationDate(new Date());
	
	Document authorObj = 
		MongoDBUtil.createDBObj(entity);
	try {

	    this.getDatabase().getCollection(
		    MongoDBUtil.getColletionNameFromEntity(
			    entity.getClass())).insertOne(authorObj);

	} catch (Exception e) {

	    throw new DALException("Error inserting 'author'", e);
	}

	entity.setId(authorObj.getObjectId("_id").toHexString());
	
	log.debug("Exiting 'save'");
	return entity;
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.dal.author.dao.AuthorDAO#findByCriteria(gz.nozing.library.dal.common.pagination.PaginationSearchDTO)
     */
    @Override
    public PaginationResultDTO<AuthorDO> findByCriteria(PaginationSearchDTO<AuthorDO> paginatedCriteria) throws DALException {
	
	log.debug("Entering 'findAuthorsByCriteria'");
	AuthorDO authorCriteria = paginatedCriteria.getSearchObj();
	
	Document query = new Document();
	String name = authorCriteria.getName() != null ? authorCriteria.getName() : "";
	query.put("name", Pattern.compile(name + ".*", Pattern.CASE_INSENSITIVE));
	
	String surname = authorCriteria.getSurname() != null ? authorCriteria.getSurname() : "";
	query.put("surname", Pattern.compile(surname + ".*", Pattern.CASE_INSENSITIVE));
	
	MongoCollection<Document> col = this.getDatabase().getCollection(
		MongoDBUtil.getColletionNameFromEntity(AuthorDO.class));
	
	/* Buscamos el número total de resultados que cumplen el filtro */
	Long count = col.count(query);
	
	/* Buscamos los autores usando los criterios y la paginación */
	FindIterable<Document> cursor = col.find(query).skip(
		paginatedCriteria.getInitialPosition()).limit(paginatedCriteria.getPageSize());

	final List<AuthorDO> authors = new LinkedList<AuthorDO>();
	if (cursor.first() != null) {

	    cursor.forEach(new AuthorDOBlock(authors));
	}
	
	PaginationResultDTO<AuthorDO> result = new PaginationResultDTO<AuthorDO>(
		paginatedCriteria.getPageSize(), paginatedCriteria.getInitialPosition(), count.intValue());
		
	result.setResult(authors);
	
	log.debug("Exiting 'findAuthorsByCriteria'");
	return result;
    }   
    
    /* (non-Javadoc)
     * @see gz.nozing.library.dal.author.dao.AuthorDAO#findByExample(gz.nozing.library.dal.author.AuthorDO)
     */
    @Override
    public List<AuthorDO> findByExample(AuthorDO criteria)
            throws DALException {

	log.debug("Entering 'findByExample'");
		
	Document query = new Document();
	String name = criteria.getName() != null ? criteria.getName() : "";
	query.put("name", Pattern.compile(name + ".*", Pattern.CASE_INSENSITIVE));
	
	String surname = criteria.getSurname() != null ? criteria.getSurname() : "";
	query.put("surname", Pattern.compile(surname + ".*", Pattern.CASE_INSENSITIVE));
	
	MongoCollection<Document> col = this.getDatabase().getCollection(
		MongoDBUtil.getColletionNameFromEntity(AuthorDO.class));
	
	FindIterable<Document> cursor = col.find(query);

	final List<AuthorDO> authors = new LinkedList<AuthorDO>();
	if (cursor.first() != null) {

	    cursor.forEach(new AuthorDOBlock(authors));
	}
	
	log.debug("Exiting 'findAuthorsByCriteria'");
	return authors;
    } 
}
