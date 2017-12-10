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
import gz.nozing.library.web.controller.form.BaseFormDTO;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * @author nozing
 *
 */
public class InsertBookFormDTO extends BaseFormDTO {

	private static final long serialVersionUID = -8287834975361860071L;

	private String title;
	private List<InsertBookAuthorFormDTO> authors;
	private String observations;
	private String category;
	private String location;
	
	public InsertBookFormDTO() { 
		super();
	}

	public String getTitle() {
		return title;
	}

	public List<InsertBookAuthorFormDTO> getAuthors() {
		return authors;
	}

	public String getObservations() {
		return observations;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthors(List<InsertBookAuthorFormDTO> authors) {
		this.authors = authors;
	}

	public void setObservations(String observations) {
		this.observations = observations;
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
		newBook.setTitle(this.getTitle());
		
		for (InsertBookAuthorFormDTO authorDTO : this.getAuthors()) {
			
			AuthorDTO authorObj = null;
			
			if (StringUtils.isEmpty(authorDTO.getId())) {
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
				authorObj = new AuthorDTO(authorDTO.getId(), authorDTO.getName());
				authorObj.setSurname(authorDTO.getSurname());
			}
			
			newBook.addAuthor(authorObj);
		}
		
		newBook.setCreationDate(new Date());
		newBook.setLastModificationDate(new Date());
		newBook.setNote(this.getObservations());
		newBook.setCategory(this.getCategory());
		newBook.setLocation(this.getLocation());
		
		return newBook;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InsertBookFormDTO ['title' : '").append(title)
				.append("', 'authors' : '").append(authors)
				.append("', 'observations' : '").append(observations)
				.append("', 'category' : '").append(category)
				.append("', 'location' : '").append(location).append("']");
		return builder.toString();
	}		
}
