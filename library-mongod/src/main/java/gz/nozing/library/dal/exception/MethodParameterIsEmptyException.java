/**
 * 
 */
package gz.nozing.library.dal.exception;

/**
 * <p>
 * Excepci�n que se lanza para indicar que un par�metro del m�todo a ejecutar
 * est� vac�o
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
