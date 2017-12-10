/**
 * 
 */
package gz.nozing.library.web.controller.author.form;

import gz.nozing.library.web.controller.form.BaseFormDTO;

/**
 * @author nozing
 *
 */
public class CreateAuthorFormDTO extends BaseFormDTO {

	private static final long serialVersionUID = 3387100269756840131L;
	
	private String name;
	private String surname;
	
	public CreateAuthorFormDTO() { }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
