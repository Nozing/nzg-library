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
public class DoubleObj extends BaseDO {

    /**
     * 
     */
    private static final long serialVersionUID = 1566980196030169370L;
    
    @DBField(name="att")
    private Double doubleAtt;
    
    public DoubleObj() { }
    
    public DoubleObj(Double doubleAtt) {
	super();
	this.doubleAtt = doubleAtt;
    }

    public Double getDoubleAtt() {
	return doubleAtt;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((doubleAtt == null) ? 0 : doubleAtt.hashCode());
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
	DoubleObj other = (DoubleObj) obj;
	if (doubleAtt == null) {
	    if (other.doubleAtt != null)
		return false;
	} else if (!doubleAtt.equals(other.doubleAtt))
	    return false;
	return true;
    }
}
