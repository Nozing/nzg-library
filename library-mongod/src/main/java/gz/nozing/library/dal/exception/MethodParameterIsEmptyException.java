/**
 * 
 */
package gz.nozing.library.dal.exception;

/**
 * <p>
 * Excepción que se lanza para indicar que un parámetro del método a ejecutar
 * está vacío
 * </p>
 * 
 * @author nozing
 * 
 */
public class MethodParameterIsEmptyException extends DALException {

    private static final long serialVersionUID = -4135311002247635670L;

    public MethodParameterIsEmptyException(String message) {
	super(message);
    }
}
