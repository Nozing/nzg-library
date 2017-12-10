/**
 * 
 */
package gz.nozing.library.web.controller.json.dto;

/**
 * <p>Clase que utilizamos para devolver un error</p>
 * 
 * @author nozing
 *
 */
public class ErrorJsonResponseDTO extends BaseJsonResponseDTO {

	private static final long serialVersionUID = 920103266283073043L;
	
	private String code;

	/**
	 * <p>Constructor por defecto de la clase</p> 
	 * 
	 * @param code <code>{@link String}</code> con el código de error
	 * @param message <code>{@link String}</code> con el mensaje que devolveremos
	 * en la respuesta
	 */
	public ErrorJsonResponseDTO(String code, String message) {
		super(JsonResponseType.ERROR, message);
		this.code = code;
	}
	
	/**
	 * <p>Devuelve el código de error de la respuesta</p>
	 * 
	 * @return Devuelve un <code>{@link String}</code> con el código de error
	 * de la respuesta
	 */
	public String getCode() {
		return code;
	}
}
