package gz.nozing.library.web.controller.json.dto;


/**
 * <p>Clase que utilizamos para devolver el resultado de una operación que ha
 * funcionado correctamente</p>
 * 
 * @author nozing
 *
 */
public class SuccessJsonResponseDTO extends BaseJsonResponseDTO {

	private static final long serialVersionUID = 6509621748131430128L;

	/**
	 * <p>Constructor por defecto de la clase</p> 
	 * 
	 * @param message <code>{@link String}</code> con el mensaje que devolveremos
	 * en la respuesta
	 */
	public SuccessJsonResponseDTO(String message) {
		
		super(JsonResponseType.SUCCESS, message);
	}
}
