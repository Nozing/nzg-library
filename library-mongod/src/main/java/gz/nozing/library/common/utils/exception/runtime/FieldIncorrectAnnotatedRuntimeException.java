/**
 * 
 */
package gz.nozing.library.common.utils.exception.runtime;

/**
 * <p>Excepci�n que se se lanza para indicar que un campo no est� anotada 
 * correctamente anotado</p>
 * 
 * @author nozing
 *
 */
public class FieldIncorrectAnnotatedRuntimeException extends ConfigurationRuntimeException {

    private static final long serialVersionUID = 8416549213008965457L;

    /**
     * Constructor por defecto
     * 
     * @param message <code>java.lang.String</code> con un mensaje explicativo
     * del error
     */
    public FieldIncorrectAnnotatedRuntimeException(String message) {
	super(message);
    }
}
