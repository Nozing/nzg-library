/**
 * 
 */
package gz.nozing.library.common.utils.exception.runtime;


/** <p>Excepción que se lanza cuando no se puede instanciar una clase.</p>
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
     * excepción que generó el error
     * @see DAOException
     */
    public InstantiatingClassException(String message,
	    Exception encapsulatedException) {

	super(message, encapsulatedException);	
    }
}
