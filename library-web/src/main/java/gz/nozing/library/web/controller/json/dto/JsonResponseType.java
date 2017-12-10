/**
 * 
 */
package gz.nozing.library.web.controller.json.dto;

/**
 * <p>Enumeraci�n que indica el tipo de respuesta que enviaremos en json</p>
 * 
 * @author nozing
 *
 */
public enum JsonResponseType {

	SUCCESS("SUCCESS"),
	WARNING("WARNING"),
	ERROR("ERROR");
	
	private String code;
	
	private JsonResponseType(String code) {

		this.code = code;
	}
	
	/**
	 * <p>Devuelve un <code>{@link String}</code> con el c�digo de la respuesta</p>
	 * 
	 * @return <code>{@link String}</code> con el c�digo de la respuesta
	 */
	public String getCode() {
		return code;
	}
}
