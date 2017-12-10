/**
 * 
 */
package gz.nozing.library.dal.shelf;

import gz.nozing.library.dal.common.BaseDO;
import gz.nozing.library.dal.common.annotation.mongo.DBField;

/**
 * @author nozing
 *
 */
public class ShelfDO extends BaseDO {

    private static final long serialVersionUID = -6126543956215978158L;

    @DBField(name="code")
    private String code;

    @DBField(name="description")
    private String description;

    /**
     * 
     */
    public ShelfDO() { }

    /**
     * @return the code
     */
    public String getCode() {
	return this.code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
	this.code = code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
	return this.description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
	result = prime * result
		+ ((this.description == null) ? 0 : this.description.hashCode());
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
	ShelfDO other = (ShelfDO) obj;
	if (this.code == null) {
	    if (other.code != null)
		return false;
	} else if (!this.code.equals(other.code))
	    return false;
	if (this.description == null) {
	    if (other.description != null)
		return false;
	} else if (!this.description.equals(other.description))
	    return false;
	return true;
    }
}
