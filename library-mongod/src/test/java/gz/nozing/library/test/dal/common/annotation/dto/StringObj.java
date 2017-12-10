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
public class StringObj extends BaseDO {

    /**
     * 
     */
    private static final long serialVersionUID = 1566980196030169370L;
    
    @DBField(name="att")
    private String stringAtt;
    
    public StringObj() { }
    
    public StringObj(String stringAtt) {
	super();
	this.stringAtt = stringAtt;
    }

    public String getStringAtt() {
	return stringAtt;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
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
	StringObj other = (StringObj) obj;
	if (stringAtt == null) {
	    if (other.stringAtt != null)
		return false;
	} else if (!stringAtt.equals(other.stringAtt))
	    return false;
	return true;
    }
}
