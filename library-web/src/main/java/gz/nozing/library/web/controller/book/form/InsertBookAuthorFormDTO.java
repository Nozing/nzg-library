/**
 * 
 */
package gz.nozing.library.web.controller.book.form;

import gz.nozing.library.web.controller.form.BaseFormDTO;

/**
 * @author nozing
 *
 */
public class InsertBookAuthorFormDTO extends BaseFormDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8795408494733406492L;
	
	private String id;
	private String name;
	private String surname;
	
	/**
	 * 
	 */
	public InsertBookAuthorFormDTO() {
		super();
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("'").append(getClass().getName())
				.append("':  { 'name' : '").append(name)
				.append("', 'surname' : '").append(surname).append("' }");
		return builder.toString();
	}
}
