/**
 * 
 */
package gz.nozing.library.dal.author;

import java.util.Date;

import gz.nozing.library.dal.common.BaseDO;
import gz.nozing.library.dal.common.annotation.mongo.DBCollection;
import gz.nozing.library.dal.common.annotation.mongo.DBField;
import gz.nozing.library.dal.common.annotation.mongo.DBKey;

/**
 * @author nozing
 *
 */
@DBCollection(name="authors")
public class AuthorDO extends BaseDO {

    private static final long serialVersionUID = -1859585988380829509L;

    @DBKey
    private String id;
    
    @DBField(name="name")
    private String name;
    
    @DBField(name="surname")
    private String surname;
    
    @DBField(name="creationDate")
    private Date creationDate;
    
    @DBField(name="modificationDate")
    private Date modificationDate;

    public AuthorDO() { 
	
	this.creationDate = new Date();
    }
    
    public String getId() {
        return id;
    }

    public AuthorDO setId(String id) {
        this.id = id;
        
        return this;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public AuthorDO setName(String name) {
        this.name = name;
        
        return this;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public AuthorDO setSurname(String surname) {
        this.surname = surname;
        
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public AuthorDO setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        
        return this;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public AuthorDO setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
        
        return this;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("{ 'AuthorDO' : { '");
	if (id != null)
	    builder.append("id' : '").append(id).append("', '");
	if (name != null)
	    builder.append("name' : '").append(name).append("', '");
	if (surname != null)
	    builder.append("surname' : '").append(surname).append("', '");
	if (creationDate != null)
	    builder.append("creationDate' : '").append(creationDate)
		    .append("', '");
	if (modificationDate != null)
	    builder.append("modificationDate' : '").append(modificationDate);
	builder.append("' } }");
	return builder.toString();
    }
}
