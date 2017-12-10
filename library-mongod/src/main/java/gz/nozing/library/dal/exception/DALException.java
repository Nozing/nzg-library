/**
 * 
 */
package gz.nozing.library.dal.exception;

/**
 * @author nozing
 *
 */
public class DALException extends Exception {

    private static final long serialVersionUID = -3590766472870509288L;

    private String message;
    private Exception encapsulatedExcepcion;

    /**
     * @param message
     */
    public DALException(String message) {
	super();
	this.message = message;
    }

    /**
     * @param message
     * @param encapsulatedExcepcion
     */
    public DALException(String message, Exception encapsulatedExcepcion) {
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
    
    @Override
    public void printStackTrace() {
	
	if (this.encapsulatedExcepcion != null) {
	    
	    this.encapsulatedExcepcion.printStackTrace();
	}
	
        super.printStackTrace();
    }
}
