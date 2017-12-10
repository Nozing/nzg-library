package gz.nozing.library.web.controller.book.form;

import java.util.ArrayList;
import java.util.List;

import gz.nozing.library.common.utils.CommonUtils;
import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.web.controller.form.PaginationFormDTO;

/**
 * @author nozing
 *
 */
public class SearchBookFormDTO extends PaginationFormDTO {

	private static final long serialVersionUID = -3065690304957607427L;
	
	private String title = null;
	private String authorName = null;
	private String authorSurname = null;
	private String category = null;
	private String location = null;
	
	/**
	 * 
	 */
	public SearchBookFormDTO() {
		super();
	}
	
	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return the authorSurname
	 */
	public String getAuthorSurname() {
		return authorSurname;
	}

	/**
	 * @param authorSurname the authorSurname to set
	 */
	public void setAuthorSurname(String authorSurname) {
		this.authorSurname = authorSurname;
	}
	
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public BookDO convertTO() {
		
		BookDO bookCriteria = new BookDO();
		bookCriteria.setTitle(this.getTitle());
		
		if (!CommonUtils.isEmpty(this.getAuthorName())
				|| !CommonUtils.isEmpty(this.getAuthorSurname())) {
			
			List<AuthorDTO> authorsFromForm = new ArrayList<AuthorDTO>(1);	
			AuthorDTO authorObj = new AuthorDTO(
					this.getAuthorName().isEmpty() ? null : this.getAuthorName());
			authorObj.setSurname(
					this.getAuthorSurname().isEmpty() ? null : this.getAuthorSurname());
			
			authorsFromForm.add(authorObj);
			bookCriteria.setAuthors(authorsFromForm);
		}
		
		bookCriteria.setCategory(this.getCategory());
		bookCriteria.setLocation(this.getLocation());
		
		return bookCriteria;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchBookFormDTO [title=");
		builder.append(title);
		builder.append(", authorName=");
		builder.append(authorName);
		builder.append(", authorSurname=");
		builder.append(authorSurname);
		builder.append("]");
		return builder.toString();
	}
}
