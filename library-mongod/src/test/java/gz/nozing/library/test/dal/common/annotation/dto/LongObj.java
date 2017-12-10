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
public class LongObj extends BaseDO {

    /**
     * 
     */
    private static final long serialVersionUID = 1566980196030169370L;
    
    @DBField(name="att")
    private Long longAtt;
    
    public LongObj() { }
    
    public LongObj(Long longAtt) {
	super();
	this.longAtt = longAtt;
    }

    public Long getLongAtt() {
	return longAtt;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((longAtt == null) ? 0 : longAtt.hashCode());
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
	LongObj other = (LongObj) obj;
	if (longAtt == null) {
	    if (other.longAtt != null)
		return false;
	} else if (!longAtt.equals(other.longAtt))
	    return false;
	return true;
    }
}
