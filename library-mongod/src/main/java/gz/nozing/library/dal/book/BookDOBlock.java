/**
 * 
 */
package gz.nozing.library.dal.book;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.bson.BsonUndefined;
import org.bson.Document;

import com.mongodb.Block;

/**
 * @author nozing
 *
 */
public class BookDOBlock implements Block<Document> {

    private static Logger log = Logger.getLogger(BookDOBlock.class);
    
    private List<BookDO> resultList;
    
    public BookDOBlock(final List<BookDO> resultList) {
	
	this.resultList = resultList;
    }
    
    @Override
    public void apply(final Document doc) {
	
	BookDO book = new BookDO();

	book.setId(doc.getObjectId("_id").toHexString());
	book.setTitle(doc.getString("title"));
	
	@SuppressWarnings("unchecked")
	List<Document> authorAsDoc = (List<Document>) doc.get("authors");
	if (CollectionUtils.isNotEmpty(authorAsDoc)) {
	    
	    for (Document authorDoc : authorAsDoc) {
		
		log.debug(authorDoc);
		
		AuthorDTO author = new AuthorDTO(
			authorDoc.getString("authorId"),
			authorDoc.getString("name"));
		
		/*
		 * Esta ñapa es debido a que por lo visto, cuando cargas un dato
		 * desde la shell de Mongo y luego desde Java intentas acceder
		 * a él, el driver te devuelve un objeto BsonUndefined en lugar 
		 * de un NULL.
		 */
		if (!(authorDoc.get("surname") instanceof BsonUndefined)) {
		    
		    author.setSurname(authorDoc.getString("surname"));
		}
		log.debug(author);
		book.addAuthor(author);
	    }
	}
	
	book.setNote(doc.getString("note"));
	book.setCreationDate(doc.getDate("creationDate"));
	book.setLastModificationDate(doc.getDate("lastModificationDate"));
	book.setLocation(doc.getString("location"));
	book.setCategory(doc.getString("category"));
	
	
	book.setLocationCode(doc.getString("locationCode"));
	book.setShelvingCode(doc.getString("shelvingCode"));
	book.setShelfCode(doc.getString("shelfCode"));
	
	if (this.resultList != null) {
	
	    this.resultList.add(book);
	}
	
	log.debug(book);
    }
}
