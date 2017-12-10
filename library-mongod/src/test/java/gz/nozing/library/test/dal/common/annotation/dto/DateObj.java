/**
 * 
 */
package gz.nozing.library.test.dal.common.annotation.dto;

import java.util.Date;

import gz.nozing.library.dal.common.BaseDO;
import gz.nozing.library.dal.common.annotation.mongo.DBField;

/**
 * @author nozing
 *
 */
public class DateObj extends BaseDO {

    private static final long serialVersionUID = 1566980196030169370L;
    
    @DBField(name="att")
    private Date dateAtt;
    
    public DateObj() { }
    
    public DateObj(Date dateAtt) {
	super();
	this.dateAtt = dateAtt;
    }

    public Date getDateAtt() {
	return dateAtt;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((dateAtt == null) ? 0 : dateAtt.hashCode());
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
	DateObj other = (DateObj) obj;
	if (dateAtt == null) {
	    if (other.dateAtt != null)
		return false;
	} else if (!dateAtt.equals(other.dateAtt))
	    return false;
	return true;
    }
}
