/**
 * 
 */
package gz.nozing.library.common.utils.exception.runtime;

/**
 * <p>Excepción que se se lanza para indicar que una clase no está anotada con 
 * la anotación esperada</p>
 * 
 * @author nozing
 *
 */
public class ClassNotAnnotatedRuntimeException extends ConfigurationRuntimeException {

    private static final long serialVersionUID = 8416549213008965457L;

    /**
     * Constructor por defecto
     * 
     * @param message <code>java.lang.String</code> con un mensaje explicativo
     * del error
     */
    public ClassNotAnnotatedRuntimeException(String message) {
	super(message);
    }
}
