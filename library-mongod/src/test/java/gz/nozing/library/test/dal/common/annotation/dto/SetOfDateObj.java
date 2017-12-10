/**
 * 
 */
package gz.nozing.library.test.dal.common.annotation.dto;

import gz.nozing.library.dal.common.BaseDO;
import gz.nozing.library.dal.common.annotation.mongo.DBField;

import java.util.Date;
import java.util.Set;

/**
 * @author nozing
 *
 */
public class SetOfDateObj extends BaseDO {

    private static final long serialVersionUID = -3262397094730479285L;

    @DBField(name="att")
    private Set<Date> setAttr;
    
    public SetOfDateObj() { }
    
    public SetOfDateObj(Set<Date> setAttr) {
	
	this.setAttr = setAttr;
    }
    
    public Set<Date> getSetAttr() {
	return setAttr;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((setAttr == null) ? 0 : setAttr.hashCode());
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
	SetOfDateObj other = (SetOfDateObj) obj;
	if (setAttr == null) {
	    if (other.setAttr != null)
		return false;
	} else if (!setAttr.equals(other.setAttr))
	    return false;
	return true;
    }
}
