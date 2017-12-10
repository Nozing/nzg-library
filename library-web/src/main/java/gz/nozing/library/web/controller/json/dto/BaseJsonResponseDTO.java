package gz.nozing.library.web.controller.json.dto;

import gz.nozing.library.dal.common.BaseDTO;

/**
 * <p>DTO que utilizaremos para devolver las respuestas JSON de las peticiones
 * Ajax. </p>
 * 
 * @author nozing
 *
 */
public abstract class BaseJsonResponseDTO extends BaseDTO {

	private static final long serialVersionUID = 3554955554047156365L;

	private JsonResponseType type = null;
	private String message;
	
	/**
	 * <p>Constructor por defecto de la clase</p> 
	 * 
	 * @param type <code>{@link JsonResponseType}</code> que indica el tipo de
	 * la respuesta
	 */
	public BaseJsonResponseDTO(JsonResponseType type) {
		super();
		this.type = type;
	}
	
	/**
	 * <p>Constructor por defecto de la clase</p> 
	 * 
	 * @param type <code>{@link JsonResponseType}</code> que indica el tipo de
	 * la respuesta
	 * @param message <code>{@link String}</code> con el mensaje que devolveremos
	 * en la respuesta
	 */
	public BaseJsonResponseDTO(JsonResponseType type, String message) {
		super();
		this.type = type;
		this.message = message;
	}

	/**
	 * <p>Devuelve el tipo de respuesta<p>
	 * 
	 * @return Devuelve un <code>{@link JsonResponseType}</code> con el tipo de
	 * la respuesta
	 */
	public JsonResponseType getType() {
		
		return this.type;
	}
	
	/**
	 * <p>Devuelve el mensaje que se senvía en la respuesta</p>
	 * 
	 * @return Devuelve un <code>{@link String}</code> con el mensaje que se 
	 * quiere indicar como respuesta
	 */
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
