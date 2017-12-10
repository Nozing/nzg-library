/**
 * 
 */
package gz.nozing.library.common.utils.exception.runtime;

/**
 * <p>Excepción genérica que se lanzará cuando ocurra algún tipo de error de 
 * configuración.</p>
 * 
 * <p>Extiende a <code>java.lang.RuntimeException</code> para que el control de
 * este tipo de fallos sea más fácil de controlar</p>
 * 
 * @author nozing
 *
 */
public class ConfigurationRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 5728352622441489331L;

    private String message;
    private Exception encapsulatedExcepcion;

    /**
     * Constructor 
     *      
     * @param message <code>java.lang.String</code> con un mensaje explcativo 
     * del error ocurrido
     */
    public ConfigurationRuntimeException(String message) {
	super();
	this.message = message;
    }

    /**
     * Constructor 
     * 
     * @param message <code>java.lang.String</code> con un mensaje explcativo 
     * del error ocurrido
     * @param encapsulatedExcepcion <code>java.lang.Exception</code> que contiene
     * la excepción que queremos encapsular
     */
    public ConfigurationRuntimeException(String message,
	    Exception encapsulatedExcepcion) {
	super();
	this.message = message;
	this.encapsulatedExcepcion = encapsulatedExcepcion;
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {

	if (this.encapsulatedExcepcion != null) {

	    return this.message + " - EncapsulatedException: '" + this.encapsulatedExcepcion.getMessage() + "'";
	}

	return this.message;
    }
}
