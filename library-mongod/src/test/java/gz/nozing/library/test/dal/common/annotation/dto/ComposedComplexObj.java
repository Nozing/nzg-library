/**
 * 
 */
package gz.nozing.library.test.dal.common.annotation.dto;

import java.util.Set;

import gz.nozing.library.dal.common.BaseDO;
import gz.nozing.library.dal.common.annotation.mongo.DBField;

/**
 * @author nozing
 *
 */
public class ComposedComplexObj extends BaseDO {

    private static final long serialVersionUID = -7724944152597077446L;

    @DBField(name="stringAtt")
    private String stringAtt;
    @DBField(name="setAtt")
    private Set<IntegerObj> integerSetAttDO;
    
    public ComposedComplexObj() { }
    
    public ComposedComplexObj(String stringAtt, Set<IntegerObj> integerSetAttDO) {
	super();
	this.stringAtt = stringAtt;
	this.integerSetAttDO = integerSetAttDO;
    }
    
    public Set<IntegerObj> getSetAttDO() {
	return integerSetAttDO;
    }
    
    public String getStringAtt() {
	return stringAtt;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((integerSetAttDO == null) ? 0 : integerSetAttDO.hashCode());
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
	ComposedComplexObj other = (ComposedComplexObj) obj;
	if (integerSetAttDO == null) {
	    if (other.integerSetAttDO != null)
		return false;
	} else if (!integerSetAttDO.equals(other.integerSetAttDO))
	    return false;
	if (stringAtt == null) {
	    if (other.stringAtt != null)
		return false;
	} else if (!stringAtt.equals(other.stringAtt))
	    return false;
	return true;
    }
}
