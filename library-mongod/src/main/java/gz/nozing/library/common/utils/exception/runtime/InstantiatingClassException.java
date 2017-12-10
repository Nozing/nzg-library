/**
 * 
 */
package gz.nozing.library.common.utils.exception.runtime;


/** <p>Excepci�n que se lanza cuando no se puede instanciar una clase.</p>
 * 
 * @author fparreno
 */
public class InstantiatingClassException extends ConfigurationRuntimeException {

    private static final long serialVersionUID = 2709246575432993245L;

    /** Constructor de la clase
     * 
     * @param message <code>java.lang.String</code> que contiene el mensaje que
     * explica el error producido
     * @see DAOException
     */
    public InstantiatingClassException(String message) {

	super(message);
    }

    /** Constructor de la clase
     * 
     * @param message <code>java.lang.String</code> que contiene el mensaje que
     * explica el error producido
     * @param encapsulatedException <code>java.lang.Exception</code> con la 
     * excepci�n que gener� el error
     * @see DAOException
     */
    public InstantiatingClassException(String message,
	    Exception encapsulatedException) {

	super(message, encapsulatedException);	
    }
}
