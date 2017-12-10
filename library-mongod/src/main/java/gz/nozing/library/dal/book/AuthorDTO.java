/**
 * 
 */
package gz.nozing.library.dal.book;

import gz.nozing.library.dal.common.BaseDTO;
import gz.nozing.library.dal.common.annotation.mongo.DBField;
import gz.nozing.library.dal.common.annotation.mongo.DBKey;

/**
 * @author nozing
 *
 */
public class AuthorDTO extends BaseDTO {

    private static final long serialVersionUID = 3381730957717312205L;
    
//    @DBField(name="authorId")
    @DBKey
    private String authorId;
    @DBField(name="name")
    private String name;
    @DBField(name="surname")
    private String surname;
    
    /**
     * @param name
     */
    public AuthorDTO(String name) { 

	this.name = name;
    }
    
    /**
     * @param authorId
     * @param name
     */
    public AuthorDTO(String authorId, String name) {
	this(name);
	this.authorId = authorId;
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
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the authorId
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("{ 'AuthorObj' : { 'authorId' : '").append(authorId)
		.append("', 'name' : '").append(name)
		.append("', 'surname' : '").append(surname).append("' } }");
	return builder.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((authorId == null) ? 0 : authorId.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((surname == null) ? 0 : surname.hashCode());
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AuthorDTO other = (AuthorDTO) obj;
	if (authorId == null) {
	    if (other.authorId != null)
		return false;
	} else if (!authorId.equals(other.authorId))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (surname == null) {
	    if (other.surname != null)
		return false;
	} else if (!surname.equals(other.surname))
	    return false;
	return true;
    }
}
