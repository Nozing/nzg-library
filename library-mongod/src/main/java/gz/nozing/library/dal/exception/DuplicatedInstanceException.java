/**
 * 
 */
package gz.nozing.library.dal.exception;

/**
 * @author nozing
 *
 */
public class DuplicatedInstanceException extends DALException {

	private static final long serialVersionUID = 1267633773910161345L;

	public DuplicatedInstanceException(String message) {
		super(message);
	}
}
