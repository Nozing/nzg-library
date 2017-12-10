package gz.nozing.library.web.controller.json.dto;

/**
 * <p>Clase que utilizamos para devolver un aviso</code>
 * 
 * @author nozing
 *
 */
public class WarningJsonResponseDTO extends BaseJsonResponseDTO {

	private static final long serialVersionUID = 6613663107487356159L;
	
	/**
	 * <p>Constructor por defecto de la clase</p> 
	 * 
	 * @param message <code>{@link String}</code> con el mensaje que devolveremos
	 * en la respuesta
	 */
	public WarningJsonResponseDTO(String message) {
		super(JsonResponseType.WARNING, message);
	}	
}
