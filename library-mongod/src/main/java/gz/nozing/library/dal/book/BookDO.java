/**
 * 
 */
package gz.nozing.library.dal.book;

import gz.nozing.library.dal.common.BaseDO;
import gz.nozing.library.dal.common.annotation.mongo.DBCollection;
import gz.nozing.library.dal.common.annotation.mongo.DBField;
import gz.nozing.library.dal.common.annotation.mongo.DBKey;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author nozing
 *
 */
@DBCollection(name="books")
public class BookDO extends BaseDO {

    private static final long serialVersionUID = -2466223524446564586L;

    // TODO Añadir valores de obligatoriedad para la creación --> mandatory = [true|false] (default = false)
    @DBKey
    private String id;
    @DBField(name="title")
    private String title;
    @DBField(name="authors")
    private List<AuthorDTO> authors;
    @DBField(name="note")
    private String note;
    @DBField(name="creationDate")
    private Date creationDate;
    @DBField(name="lastModificationDate")
    private Date lastModificationDate;
    @DBField(name="category")
    private String category;
    @DBField(name="location")
    private String location;
    @DBField(name="locationCode")
    private String locationCode;
    @DBField(name="shelvingCode")
    private String shelvingCode;
    @DBField(name="shelfCode")
    private String shelfCode;

    /**
     * 
     */
    public BookDO() {
	
	super();
	this.authors = new LinkedList<AuthorDTO>();
    }

    /**
     * @param title
     * @param author
     */
    public BookDO(String title, AuthorDTO author) {
	this();
	this.title = title;
	
	this.authors.add(author);
    }

    /**
     * @return the id
     */
    public String getId() {
	return this.id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
	this.id = id;
    }
    /**
     * @return the title
     */
    public String getTitle() {
	return this.title;
    }
    /**
     * @param titles the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    /**
     * @return the author
     */
    public List<AuthorDTO> getAuthors() {

	return this.authors;
    }
    /**
     * @param author the author to set
     */
    public BookDO setAuthors(List<AuthorDTO> authors) {

	this.authors = authors;
	return this;
    }
    
    public void addAuthor(AuthorDTO author) {
	
	this.authors.add(author);
    }
    /**
     * @return the note
     */
    public String getNote() {
	return this.note;
    }
    /**
     * @param description the description to set
     */
    public void setNote(String note) {
	this.note = note;
    }
    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
	return this.creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    /**
     * @return the lastModificationDate
     */
    public Date getLastModificationDate() {
	return this.lastModificationDate;
    }

    /**
     * @param lastModificationDate the lastModificationDate to set
     */
    public void setLastModificationDate(Date lastModificationDate) {
	this.lastModificationDate = lastModificationDate;
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
    public BookDO setCategory(String category) {
        this.category = category;
        
        return this;
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
    public BookDO setLocation(String location) {
        this.location = location;
        
        return this;
    }

    /**
     * @return the locationCode
     */
    public String getLocationCode() {
	return this.locationCode;
    }

    /**
     * @param locationCode the locationCode to set
     */
    public void setLocationCode(String locationCode) {
	this.locationCode = locationCode;
    }

    /**
     * @return the shelvingCode
     */
    public String getShelvingCode() {
	return this.shelvingCode;
    }

    /**
     * @param shelvingCode the shelvingCode to set
     */
    public void setShelvingCode(String shelvingCode) {
	this.shelvingCode = shelvingCode;
    }

    /**
     * @return the shelfCode
     */
    public String getShelfCode() {
	return this.shelfCode;
    }

    /**
     * @param shelfCode the shelfCode to set
     */
    public void setShelfCode(String shelfCode) {
	this.shelfCode = shelfCode;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("BookDO ['id' : '").append(id).append("', 'title' : '")
		.append(title).append("', 'authors' : '").append(authors)
		.append("', 'note' : '").append(note)
		.append("', 'creationDate' : '").append(creationDate)
		.append("', 'lastModificationDate' : '")
		.append(lastModificationDate).append("', 'category' : '")
		.append(category).append("', 'location' : '").append(location)
		.append("', 'locationCode' : '").append(locationCode)
		.append("', 'shelvingCode' : '").append(shelvingCode)
		.append("', 'shelfCode' : '").append(shelfCode).append("']");
	return builder.toString();
    } 
}
