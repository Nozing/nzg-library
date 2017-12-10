/**
 * 
 */
package gz.nozing.library.web.controller.book.form;

import gz.nozing.library.core.command.author.CreateNewAuthorCoreCmd;
import gz.nozing.library.core.command.common.CoreContext;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.author.AuthorDO;
import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.web.controller.book.dto.AuthorBookWebDTO;
import gz.nozing.library.web.controller.form.BaseFormDTO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * @author nozing
 *
 */
public class UpdateBookFormDTO extends BaseFormDTO {

	private static final long serialVersionUID = -1853125602278339898L;

	private String id;
	private String title;
	private List<AuthorBookWebDTO> authors;
	private String note;
	private Date creationDate;
	private String category;
	private String location;
	
	public UpdateBookFormDTO() {
		
		this.authors = new LinkedList<AuthorBookWebDTO>();
	}
	
	public UpdateBookFormDTO(String id) {
		
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorBookWebDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorBookWebDTO> authors) {
		this.authors = authors;
	}

	public void addAuthor(AuthorBookWebDTO author) {
		
		this.authors.add(author);
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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

	public BookDO convertTo(CoreContext context) throws CoreException {
		
		BookDO newBook = new BookDO();
		newBook.setId(this.getId());
		newBook.setTitle(this.getTitle());
		
		for (AuthorBookWebDTO authorDTO : this.getAuthors()) {
			
			AuthorDTO authorObj = null;
			
			if (StringUtils.isEmpty(authorDTO.getAuthorId())) {
				/* Si no nos llega identificador desde el formulario, es que 
				 * el autor indicado es nuevo por lo que lo daremos de alta en
				 * la BBDD */
				AuthorDO authorDO = new AuthorDO();
				authorDO.setName(authorDTO.getName());
				authorDO.setSurname(authorDTO.getSurname());
				
				CreateNewAuthorCoreCmd createNewAuthorCmd =
						new CreateNewAuthorCoreCmd(authorDO);
				createNewAuthorCmd.setCoreContext(context);
				
				authorDO = createNewAuthorCmd.execute();
				
				authorObj = new AuthorDTO(authorDO.getId(), authorDO.getName());
				authorObj.setSurname(authorDO.getSurname());
				
			} else {
				
				/* El autor ya estaba dado de alta en el sistema por lo 
				 * que simplemente creamos el objeto que relacionamos con 
				 * el libro */
				authorObj = new AuthorDTO(authorDTO.getAuthorId(), authorDTO.getName());
				authorObj.setSurname(authorDTO.getSurname());
			}
			
			newBook.addAuthor(authorObj);
		}
		
		newBook.setCreationDate(new Date());
		newBook.setLastModificationDate(new Date());
		newBook.setNote(this.getNote());
		newBook.setLocation(this.getLocation());
		newBook.setCategory(this.getCategory());
		
		return newBook;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ 'UpdateBookFormDTO' : { 'bookId' : '").append(id)
				.append("', 'title' : '").append(title)
				.append("', 'authors' : '").append(authors)
				.append("', 'note' : '").append(note)
				.append("', 'creationDate' : '").append(creationDate)
				.append("' } }");
		return builder.toString();
	}
}
