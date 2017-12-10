package gz.nozing.library.web.controller.book.dto;

import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.web.controller.dto.BaseWebDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;


public class BookWebDTO extends BaseWebDTO {

	private static final long serialVersionUID = -7930415489878425324L;
	
	private static Logger log = Logger.getLogger(BookWebDTO.class);
	
	private String id;
	private String title;
	private List<AuthorBookWebDTO> authors;
	private String note;
	private Date creationDate;
	private String category;
	private String location;
	
	public BookWebDTO() {
		
		this.authors = new LinkedList<AuthorBookWebDTO>();
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

	public void addAuthor(AuthorBookWebDTO authorBook) {
		
		this.authors.add(authorBook);		
	}
	
	public static BookWebDTO buildFrom(BookDO bookDO) {
		
		log.debug(String.format("Book: %s", bookDO));
		BookWebDTO bookWeb = new BookWebDTO();
		bookWeb.setId(bookDO.getId());
		bookWeb.setTitle(bookDO.getTitle());
		
		log.trace(String.format("Number of authors %s", 
				bookDO.getAuthors().size()));

		List<AuthorBookWebDTO> authors = new ArrayList<AuthorBookWebDTO>(
				bookDO.getAuthors().size());
		for (AuthorDTO author : bookDO.getAuthors()) {

			log.trace(String.format("Author: %s", author));
			AuthorBookWebDTO authorBook = new AuthorBookWebDTO(
					author.getAuthorId(), author.getName());
			authorBook.setSurname(author.getSurname());

			authors.add(authorBook);
		}

		bookWeb.setAuthors(authors);
		
		bookWeb.setNote(bookDO.getNote());
		bookWeb.setCategory(bookDO.getCategory());
		bookWeb.setLocation(bookDO.getLocation());
		
		log.debug(String.format("BookWeb: %s", bookWeb));
		
		return bookWeb;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookWebDTO ['id' : '").append(id)
				.append("', 'title' : '").append(title)
				.append("', 'authors' : '").append(authors)
				.append("', 'note' : '").append(note)
				.append("', 'creationDate' : '").append(creationDate)
				.append("', 'category' : '").append(category)
				.append("', 'location' : '").append(location).append("']");
		return builder.toString();
	}
}
