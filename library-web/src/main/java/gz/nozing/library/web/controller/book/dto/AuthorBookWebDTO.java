/**
 * 
 */
package gz.nozing.library.web.controller.book.dto;

import gz.nozing.library.web.controller.dto.BaseWebDTO;

/**
 * @author nozing
 *
 */
public class AuthorBookWebDTO extends BaseWebDTO {

	private static final long serialVersionUID = -8712134216969239789L;

	private String authorId;
	private String name;
	private String surname;
	
	/**
	 * 
	 */
	public AuthorBookWebDTO() {
		
		super();
	}
	
	/**
	 * @param name
	 */
	public AuthorBookWebDTO(String authorId, String name) { 
		
		this();
		this.authorId = authorId;
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getAuthorId() {
		return authorId;
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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
