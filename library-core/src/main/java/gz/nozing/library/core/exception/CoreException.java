/**
 * 
 */
package gz.nozing.library.core.exception;

/**
 * @author nozing
 *
 */
public class CoreException extends Exception {

    private static final long serialVersionUID = -2329254903512068703L;

    private String message;
    private Exception encapsulatedExcepcion;

    /**
     * @param message
     */
    public CoreException(String message) {
	super();
	this.message = message;
    }

    /**
     * @param message
     * @param encapsulatedExcepcion
     */
    public CoreException(String message, Exception encapsulatedExcepcion) {
	super();
	this.message = message;
	this.encapsulatedExcepcion = encapsulatedExcepcion;
    }

    @Override
    public String getMessage() {

	if (this.encapsulatedExcepcion != null) {

	    return this.message + " - EncapsulatedException: '" + this.encapsulatedExcepcion.getMessage() + "'";
	}

	return this.message;
    }
}
