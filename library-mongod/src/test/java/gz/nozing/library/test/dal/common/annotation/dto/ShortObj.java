/**
 * 
 */
package gz.nozing.library.test.dal.common.annotation.dto;

import gz.nozing.library.dal.common.BaseDO;
import gz.nozing.library.dal.common.annotation.mongo.DBField;

/**
 * @author nozing
 *
 */
public class ShortObj extends BaseDO {

    /**
     * 
     */
    private static final long serialVersionUID = 1566980196030169370L;
    
    @DBField(name="att")
    private Short shortAtt;
    
    public ShortObj() { }
    
    public ShortObj(Short shortAtt) {
	super();
	this.shortAtt = shortAtt;
    }

    public Short getShortAtt() {
	return shortAtt;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((shortAtt == null) ? 0 : shortAtt.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ShortObj other = (ShortObj) obj;
	if (shortAtt == null) {
	    if (other.shortAtt != null)
		return false;
	} else if (!shortAtt.equals(other.shortAtt))
	    return false;
	return true;
    }
}
