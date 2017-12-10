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
public class ComposedSimpleObj extends BaseDO {

    private static final long serialVersionUID = -7724944152597077446L;

    @DBField(name="stringAtt")
    private String stringAtt;
    @DBField(name="integerAtt")
    private IntegerObj integerAttDO;
    
    public ComposedSimpleObj() { }
    
    public ComposedSimpleObj(String stringAtt, IntegerObj integerAttDO) {
	super();
	this.stringAtt = stringAtt;
	this.integerAttDO = integerAttDO;
    }
    
    public IntegerObj getIntegerAttDO() {
	return integerAttDO;
    }
    
    public String getStringAtt() {
	return stringAtt;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((integerAttDO == null) ? 0 : integerAttDO.hashCode());
	result = prime * result
		+ ((stringAtt == null) ? 0 : stringAtt.hashCode());
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
	ComposedSimpleObj other = (ComposedSimpleObj) obj;
	if (integerAttDO == null) {
	    if (other.integerAttDO != null)
		return false;
	} else if (!integerAttDO.equals(other.integerAttDO))
	    return false;
	if (stringAtt == null) {
	    if (other.stringAtt != null)
		return false;
	} else if (!stringAtt.equals(other.stringAtt))
	    return false;
	return true;
    }
}
