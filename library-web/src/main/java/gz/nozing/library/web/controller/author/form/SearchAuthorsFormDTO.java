/**
 * 
 */
package gz.nozing.library.web.controller.author.form;

import gz.nozing.library.web.controller.form.PaginationFormDTO;

/**
 * @author nozing
 *
 */
public class SearchAuthorsFormDTO extends PaginationFormDTO {

	private static final long serialVersionUID = 7061750456698653600L;

	private String name;
	private String surname;
	
	/**
	 * 
	 */
	public SearchAuthorsFormDTO() {
		super();
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
