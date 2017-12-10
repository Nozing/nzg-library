/**
 * 
 */
package gz.nozing.library.core.exception;

/**
 * <p>
 * Excepci�n que indica que no se encuentra una entidad
 * </p>
 * 
 * @author nozing
 *
 */
public class EntityNotFoundCoreException extends CoreException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -410971885253522862L;

	/**
	 * @param message <code>{@link String}</code> con el mensaje 
	 * explicativo que provoca la excepci�n
	 */
	public EntityNotFoundCoreException(String message) {
		super(message);
	}

	/**
	 * @param message <code>{@link String}</code> con el mensaje 
	 * explicativo que provoca la excepci�n
	 * @param encapsulatedExcepcion <code>{@link Exceptio}</code> que encapsulamos
	 * y que ha provocado el error
	 */
	public EntityNotFoundCoreException(String message,
			Exception encapsulatedExcepcion) {
		super(message, encapsulatedExcepcion);
	}

}
