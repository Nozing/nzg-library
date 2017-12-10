/**
 * 
 */
package gz.nozing.library.dal.location;

import gz.nozing.library.dal.common.BaseDO;
import gz.nozing.library.dal.common.annotation.mongo.DBCollection;
import gz.nozing.library.dal.common.annotation.mongo.DBField;
import gz.nozing.library.dal.common.annotation.mongo.DBKey;
import gz.nozing.library.dal.shelving.ShelvingDO;

import java.util.HashSet;
import java.util.Set;

/**
 * @author nozing
 *
 */
@DBCollection(name="locations")
public class LocationDO extends BaseDO {

    private static final long serialVersionUID = 7799344968055463476L;

    @DBKey
    private String id;
    @DBField(name="code")
    private String code;
    @DBField(name="description")
    private String description;
    @DBField(name="shelving")
    private Set<ShelvingDO> shelvings;

    /**
     * 
     */
    public LocationDO() {
	super();
	this.shelvings = new HashSet<ShelvingDO>();
    }
    /**
     * @return the id
     */
    public String getId() {
	return this.id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
	this.id = id;
    }


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
    /**
     * @return the shelving
     */
    public Set<ShelvingDO> getShelvings() {
	return this.shelvings;
    }
    /**
     * @param shelving the shelving to set
     */
    public void setShelvings(Set<ShelvingDO> shelvings) {
	this.shelvings = shelvings;
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
	result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
	LocationDO other = (LocationDO) obj;
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
	if (this.id == null) {
	    if (other.id != null)
		return false;
	} else if (!this.id.equals(other.id))
	    return false;
	return true;
    }


}
