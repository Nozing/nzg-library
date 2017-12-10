/**
 * 
 */
package gz.nozing.library.dal.exception;

/**
 * @author nozing
 *
 */
public class EntityNotFoundException extends DALException {

	private static final long serialVersionUID = -4135311002247635670L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
