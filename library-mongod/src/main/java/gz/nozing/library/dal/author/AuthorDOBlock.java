/**
 * 
 */
package gz.nozing.library.dal.author;

import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.Block;

/**
 * @author nozing
 *
 */
public class AuthorDOBlock implements Block<Document> {

    @SuppressWarnings("unused")
    private static Logger log = Logger.getLogger(AuthorDOBlock.class);
    
    private List<AuthorDO> resultList;
    
    /**
     * @param resultList
     */
    public AuthorDOBlock(final List<AuthorDO> resultList) {
	
	this.resultList = resultList;
    }
    
    /* (non-Javadoc)
     * @see com.mongodb.Block#apply(java.lang.Object)
     */
    @Override
    public void apply(final Document doc) {
	
	AuthorDO author = new AuthorDO();

	author.setId(doc.getObjectId("_id").toHexString());
	author.setName(doc.getString("name"));
	author.setSurname(doc.getString("surname"));
	author.setCreationDate(doc.getDate("creationDate"));
	author.setModificationDate(doc.getDate("modificationDate"));	
	
	if (this.resultList != null) {
	
	    this.resultList.add(author);
	}
    }
}
