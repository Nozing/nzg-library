/**
 * 
 */
package gz.nozing.library.web.controller.author.form;

import gz.nozing.library.web.controller.form.BaseFormDTO;

/**
 * @author nozing
 *
 */
public class UpdateAuthorFormDTO extends BaseFormDTO {

	private static final long serialVersionUID = 2747336870187592444L;

	private String id;
	private String name;
	private String surname;
	
	/**
	 * 
	 */
	public UpdateAuthorFormDTO() {	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
