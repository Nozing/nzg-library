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
public class IntegerObj extends BaseDO {

    /**
     * 
     */
    private static final long serialVersionUID = 1566980196030169370L;
    
    @DBField(name="att")
    private Integer integerAtt;
    
    public IntegerObj() { }
    
    public IntegerObj(Integer integerAtt) {
	super();
	this.integerAtt = integerAtt;
    }

    public Integer getIntegerAtt() {
	return integerAtt;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((integerAtt == null) ? 0 : integerAtt.hashCode());
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
	IntegerObj other = (IntegerObj) obj;
	if (integerAtt == null) {
	    if (other.integerAtt != null)
		return false;
	} else if (!integerAtt.equals(other.integerAtt))
	    return false;
	return true;
    }
}
