/**
 * 
 */
package gz.nozing.library.test.dal.book;

import gz.nozing.library.dal.author.AuthorDO;
import gz.nozing.library.dal.author.dao.AuthorDAO;
import gz.nozing.library.dal.author.dao.AuthorDAOImpl;
import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.book.dao.BookDAOImpl;
import gz.nozing.library.dal.common.pagination.PaginationResultDTO;
import gz.nozing.library.dal.common.pagination.PaginationSearchDTO;
import gz.nozing.library.dal.exception.DALException;
import gz.nozing.library.dal.exception.EntityNotFoundException;
import gz.nozing.library.test.dal.util.TestDALUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

/**
 * @author nozing
 * 
 */
public class BookDAOTest {

    private static Logger log = Logger.getLogger(BookDAOTest.class);

    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    
    private List<BookDO> books;
    private List<AuthorDO> authors;


    public BookDAOTest() {

	this.bookDAO = new BookDAOImpl();
	this.bookDAO.setDatabase(TestDALUtils.instance().getDatabase());

	this.authorDAO = new AuthorDAOImpl();
	this.authorDAO.setDatabase(TestDALUtils.instance().getDatabase());
	
	this.books = new LinkedList<BookDO>();
	this.authors = new LinkedList<AuthorDO>();
    }

    private AuthorDTO createAuthorDTO(AuthorDO authorDO) {
	 
	if (authorDO.getId() == null) {
	    
	    try {
		authorDO = this.authorDAO.save(authorDO);
		this.authors.add(authorDO);
	    } catch (DALException e) {

		Assert.fail("Can't create author for testing");
	    }
	}
	
	AuthorDTO authorDTO = new AuthorDTO(
		authorDO.getId(), authorDO.getName());
	authorDTO.setSurname(authorDO.getSurname());
	
	this.authors.add(authorDO);
	
	return authorDTO;
    }
    
    @SuppressWarnings("serial")
    @Test
    public void createTest() throws DALException {

	log.info("Test started");

	AuthorDO authorDO = new AuthorDO();
	authorDO.setName("Name Author1");	
	final AuthorDTO author1 = this.createAuthorDTO(authorDO);
	
	authorDO = new AuthorDO();
	authorDO.setName("Name Author2");
	authorDO.setSurname("Surname Author2");
	final AuthorDTO author2 = this.createAuthorDTO(authorDO);
	
	BookDO temporalBook = new BookDO();
	temporalBook.setTitle("Titulo");
	temporalBook.setAuthors(new LinkedList<AuthorDTO>() {
	    {
		add(author1);
		add(author2);
	    }
	});
	temporalBook.setNote("Some note about the content");
	temporalBook.setLocationCode("LOC01");
	temporalBook.setShelvingCode("Shelv01");
	temporalBook.setShelfCode("Shelf-01");

	BookDO testBook01 = this.bookDAO.save(temporalBook);

	this.books.add(testBook01);

	Assert.assertNotNull("Book inserted not retrieved", testBook01);
	Assert.assertNotNull("New 'id' can't be null", testBook01.getId());

	Assert.assertEquals("'title' has been modified", testBook01.getTitle(),
		temporalBook.getTitle());
	Assert.assertEquals("'note' has been modified", testBook01.getNote(),
		temporalBook.getNote());
	Assert.assertEquals("'locationCode' has been modified",
		testBook01.getLocationCode(), temporalBook.getLocationCode());
	Assert.assertEquals("'shelvingCode' has been modified",
		testBook01.getShelvingCode(), temporalBook.getShelvingCode());
	Assert.assertEquals("'shelfCode' has been modified",
		testBook01.getShelfCode(), temporalBook.getShelfCode());

	Assert.assertTrue(testBook01.getAuthors().size() == temporalBook
		.getAuthors().size());
	Assert.assertTrue(testBook01.getAuthors().contains(author1));
	Assert.assertTrue(testBook01.getAuthors().contains(author2));

	for (AuthorDTO author : testBook01.getAuthors()) {

	    String authorId = author.getAuthorId();
	    Assert.assertNotNull(authorId);
	    if (authorId.equals(author1.getAuthorId())
		    || authorId.equals(author2.getAuthorId())) {

		if (authorId.equals(author1.getAuthorId())) {

		    Assert.assertEquals("'name' is not equals to author1 name",
			    author.getName(), author1.getName());
		    Assert.assertEquals(
			    "'surname' is not equals to author1 surname",
			    author.getSurname(), author1.getSurname());
		} else {

		    Assert.assertEquals("'name' is not equals to author2 name",
			    author.getName(), author2.getName());
		    Assert.assertEquals(
			    "'surname' is not equals to author2 surname",
			    author.getSurname(), author2.getSurname());

		}
	    }
	}

	log.info("Test finished");
    }

    @Test
    public void findByIdTest() throws DALException {

	log.info("Test started");

	AuthorDO authorDO = new AuthorDO();
	authorDO.setName("Name Author1");	
	final AuthorDTO author1 = this.createAuthorDTO(authorDO);
	
	authorDO = new AuthorDO();
	authorDO.setName("Name Author2");
	authorDO.setSurname("Surname Author2");
	final AuthorDTO author2 = this.createAuthorDTO(authorDO);
	
	BookDO temporalBook = new BookDO("Titulo1", author1);
	temporalBook.addAuthor(author2);

	BookDO testBook01 = this.bookDAO.save(temporalBook);
	this.books.add(testBook01);

	log.info("Searching book with id '" + testBook01.getId() + "'");
	BookDO searchedBook = this.bookDAO.findEntityById(testBook01.getId());

	Assert.assertEquals("'id' values have to be equals: ",
		testBook01.getId(), searchedBook.getId());
	Assert.assertEquals("'title' values have to be equals: ",
		testBook01.getTitle(), searchedBook.getTitle());

	Assert.assertFalse(searchedBook.getAuthors().isEmpty());
	for (AuthorDTO author : searchedBook.getAuthors()) {

	    String authorId = author.getAuthorId();
	    Assert.assertNotNull(authorId);
	    if (authorId.equals(author1.getAuthorId())
		    || authorId.equals(author2.getAuthorId())) {

		if (authorId.equals(author1.getAuthorId())) {

		    Assert.assertEquals("'name' is not equals to author1 name",
			    author.getName(), author1.getName());
		    Assert.assertEquals(
			    "'surname' is not equals to author1 surname",
			    author.getSurname(), author1.getSurname());
		} else {

		    Assert.assertEquals("'name' is not equals to author2 name",
			    author.getName(), author2.getName());
		    Assert.assertEquals(
			    "'surname' is not equals to author2 surname",
			    author.getSurname(), author2.getSurname());

		}
	    }
	}

	log.info("Test finished");
    }

    @Test
    public void testUpdateTitle() throws Exception {

	log.info("Test started");
	
	BookDO book = new BookDO("Titulo1", this.createAuthorDTO(
		new AuthorDO().setName("NombreAutor1")));
	book.setNote("Descripción");

	BookDO originalBook = this.bookDAO.save(book);

	this.books.add(originalBook);

	Thread.sleep(2000);

	Date originalLastModificationDate = originalBook
		.getLastModificationDate();
	String newTitle = "New title";
	originalBook.setTitle(newTitle);

	BookDO updatedBook = this.bookDAO.update(originalBook);

	Assert.assertEquals("'title' has not been updated. ", newTitle,
		updatedBook.getTitle());
	Assert.assertTrue("'lastModifiedDate' can't be equals",
		originalLastModificationDate.before(updatedBook
			.getLastModificationDate()));
    }

    @Test
    public void updateTest() throws Exception {

	log.info("Test started");

	final AuthorDTO author1 = this.createAuthorDTO(new AuthorDO()
		.setName("NombreAutor1"));
	final AuthorDTO author2 = this.createAuthorDTO(new AuthorDO()
		.setName("NombreAutor2"));

	BookDO testUpdateBook = new BookDO("Titulo1", author1);
	testUpdateBook.setNote("Descripción");

	testUpdateBook = this.bookDAO.save(testUpdateBook);

	this.books.add(testUpdateBook);

	String originalNote = testUpdateBook.getNote();

	@SuppressWarnings("serial")
	List<AuthorDTO> authors = new LinkedList<AuthorDTO>() {
	    {
		add(author1);
		add(author2);
	    }
	};
	String textToSet = "Modificado";

	testUpdateBook.setTitle(textToSet);
	testUpdateBook.setAuthors(authors);

	Thread.sleep(2000);
	Date originalLastModifiedDate = testUpdateBook
		.getLastModificationDate();
	BookDO bookUpdated = this.bookDAO.update(testUpdateBook);

	Assert.assertEquals("'title' has not been updated. ", textToSet,
		bookUpdated.getTitle());

	Assert.assertEquals("'description' has been modified", originalNote,
		bookUpdated.getNote());

	Assert.assertEquals("'creationDate' has been modified. ",
		testUpdateBook.getCreationDate(), bookUpdated.getCreationDate());

	Assert.assertTrue("'lastModificationDate' has been modified. ",
		originalLastModifiedDate.before(bookUpdated
			.getLastModificationDate()));

	Assert.assertFalse(bookUpdated.getAuthors().isEmpty());
	for (AuthorDTO author : bookUpdated.getAuthors()) {

	    String authorId = author.getAuthorId();
	    Assert.assertNotNull(authorId);
	    if (authorId.equals(author1.getAuthorId())
		    || authorId.equals(author2.getAuthorId())) {

		if (authorId.equals(author1.getAuthorId())) {

		    Assert.assertEquals("'name' is not equals to author1 name",
			    author.getName(), author1.getName());
		    Assert.assertEquals(
			    "'surname' is not equals to author1 surname",
			    author.getSurname(), author1.getSurname());
		} else {

		    Assert.assertEquals("'name' is not equals to author2 name",
			    author.getName(), author2.getName());
		    Assert.assertEquals(
			    "'surname' is not equals to author2 surname",
			    author.getSurname(), author2.getSurname());

		}
	    }
	}

	// Actualizamos los autores a null para comprobar que se eliminan
	bookUpdated.setAuthors(null);

	BookDO bookWithoutAuthors = this.bookDAO.update(bookUpdated);

	Assert.assertNotNull("'authors' can't be null",
		bookWithoutAuthors.getAuthors());
	Assert.assertTrue("'authors' has to be empty", bookWithoutAuthors
		.getAuthors().isEmpty());
    }

    @Test
    public void deleteTest() throws DALException {

	log.info("Test started");

	AuthorDTO author1 = this.createAuthorDTO(new AuthorDO().setName("NombreAutor1"));

	BookDO testBook01 = this.bookDAO.save(new BookDO("Titulo1", author1));

	log.info(String.format("Deleting book with id '%s'", testBook01.getId()));
	this.bookDAO.delete(testBook01);

	BookDO searchedBook = null;
	try {

	    searchedBook = this.bookDAO.findEntityById(testBook01.getId());

	} catch (EntityNotFoundException enfe) {
	}

	Assert.assertNull(
		String.format("Book '%s' has not been deleted",
			testBook01.getId()), searchedBook);

	log.info("Test finished");
    }

    @Test
    public void testFindByCriteria() throws DALException {

	log.info("Test started");
	List<BookDO> result = null;
	AuthorDTO author1 = this.createAuthorDTO(new AuthorDO().setName("NombreAutor1"));

	BookDO testBook01 = new BookDO("Titulo1", author1);
	testBook01 = this.bookDAO.save(testBook01);

	this.books.add(testBook01);

	BookDO bookCriteria = new BookDO();
	bookCriteria.setTitle(testBook01.getTitle());

	result = this.bookDAO.find(bookCriteria);

	Assert.assertNotNull("Result can't be null", result);
	Assert.assertFalse("Result can't be empty", result.isEmpty());
	Assert.assertEquals("'title' values aren't equals",
		testBook01.getTitle(), result.get(0).getTitle());

	bookCriteria.addAuthor(author1);

	result = this.bookDAO.find(bookCriteria);

	Assert.assertNotNull("Result can't be null", result);
	Assert.assertFalse("Result can't be empty", result.isEmpty());

	BookDO bookRetrieved = result.get(0);
	Assert.assertEquals("'title' values aren't equals",
		testBook01.getTitle(), bookRetrieved.getTitle());
	Assert.assertNotNull("'author' can't be null",
		bookRetrieved.getAuthors());
	Assert.assertNotNull("'author' can't be empty",
		bookRetrieved.getAuthors());

	log.info("Test finished");
    }

    @Test
    public void testFindBooksPaginatedByAuthorName() throws DALException {

	Integer initialPosition = 1;
	Integer pageSize = 2;

	BookDO bookExample = new BookDO("Titulo1",
		this.createAuthorDTO(new AuthorDO().setName("NombreAutor1")));
	bookExample = this.bookDAO.save(bookExample);
	this.books.add(bookExample);

	bookExample = new BookDO("Titulo2", new AuthorDTO("1", "NombreAutor2"));
	bookExample = this.bookDAO.save(bookExample);
	this.books.add(bookExample);

	BookDO bookCriteria = new BookDO();
	AuthorDTO author = new AuthorDTO("NombreAutor1");

	bookCriteria.addAuthor(author);

	PaginationSearchDTO<BookDO> paginationCriteria = new PaginationSearchDTO<BookDO>(
		bookCriteria, initialPosition, pageSize);

	PaginationResultDTO<BookDO> result = this.bookDAO
		.findBooksPaginated(paginationCriteria);

	Assert.assertNotNull("Result can't be null", result);
	Assert.assertEquals("Only one return was expected", new Integer(1),
		new Integer(result.getResult().size()));

	BookDO book = result.getResult().iterator().next();
	Assert.assertNotNull("Author can't be null", book.getAuthors());
	Assert.assertFalse("Author can't be empty", book.getAuthors().isEmpty());
    }

    @Test
    public void testFindBooksPaginatedByAuthorNameAndSurname()
	    throws DALException {

	Integer initialPosition = 1;
	Integer pageSize = 2;

	AuthorDTO authorExample = this.createAuthorDTO(new AuthorDO().setName("NombreAutor1"));
	authorExample.setSurname("Apellido");

	BookDO bookExample = new BookDO("Titulo1", authorExample);
	bookExample = this.bookDAO.save(bookExample);
	this.books.add(bookExample);

	bookExample = new BookDO("Titulo2", this.createAuthorDTO(new AuthorDO().setName("NombreAutor2")));
	bookExample = this.bookDAO.save(bookExample);
	this.books.add(bookExample);

	BookDO bookCriteria = new BookDO();
	AuthorDTO authorCriteria = new AuthorDTO("NombreAutor1");
	authorCriteria.setSurname("Apellido");

	bookCriteria.addAuthor(authorCriteria);

	PaginationSearchDTO<BookDO> paginationCriteria = new PaginationSearchDTO<BookDO>(
		bookCriteria, initialPosition, pageSize);

	PaginationResultDTO<BookDO> result = this.bookDAO
		.findBooksPaginated(paginationCriteria);

	Assert.assertNotNull("Result can't be null", result);
	Assert.assertEquals("Only one return was expected", new Integer(1),
		new Integer(result.getResult().size()));

	BookDO book = result.getResult().iterator().next();
	Assert.assertNotNull("Author can't be null", book.getAuthors());
	Assert.assertFalse("Author can't be empty", book.getAuthors().isEmpty());
    }

    @Test
    public void testFindBooksPaginatedByAuthorSurname() throws DALException {

	Integer initialPosition = 1;
	Integer pageSize = 2;

	AuthorDTO authorExample = this.createAuthorDTO(
		new AuthorDO().setName("NombreAutor1").setSurname("Apellido"));

	BookDO bookExample = new BookDO("Titulo1", authorExample);
	bookExample = this.bookDAO.save(bookExample);
	this.books.add(bookExample);

	bookExample = new BookDO("Titulo2", this.createAuthorDTO(new AuthorDO()
		.setName("NombreAutor2")));
	bookExample = this.bookDAO.save(bookExample);
	this.books.add(bookExample);

	BookDO bookCriteria = new BookDO();
	AuthorDTO authorCriteria = new AuthorDTO("NombreAutor1");
	authorCriteria.setSurname("Apellido");

	bookCriteria.addAuthor(authorCriteria);

	PaginationResultDTO<BookDO> result = this.bookDAO
		.findBooksPaginated(new PaginationSearchDTO<BookDO>(
			bookCriteria, initialPosition, pageSize));

	Assert.assertNotNull("Result can't be null", result);
	Assert.assertEquals("Only one return was expected", new Integer(1),
		new Integer(result.getResult().size()));

	BookDO book = result.getResult().iterator().next();
	Assert.assertNotNull("Author can't be null", book.getAuthors());
	Assert.assertFalse("Author can't be empty", book.getAuthors().isEmpty());
    }

    @Test
    public void testFindBooksPaginated() throws DALException {

	Integer initialPosition = 1;
	Integer pageSize = 2;

	AuthorDTO author1 = this.createAuthorDTO(new AuthorDO()
		.setName("NombreAutor1"));

	BookDO bookExample = new BookDO("Titulo1", author1);
	bookExample = this.bookDAO.save(bookExample);

	this.books.add(bookExample);

	bookExample = new BookDO("Titulo2", author1);
	bookExample = this.bookDAO.save(bookExample);
	this.books.add(bookExample);

	bookExample = new BookDO("Titulo3", author1);
	bookExample.addAuthor(this.createAuthorDTO(new AuthorDO().setName("NombreAutor2")));
	bookExample = this.bookDAO.save(bookExample);
	this.books.add(bookExample);

	bookExample = new BookDO("Titulo4", author1);
	bookExample.addAuthor(this.createAuthorDTO(new AuthorDO().setName("NombreAutor2")));
	bookExample.addAuthor(this.createAuthorDTO(new AuthorDO().setName("NombreAutor3")));
	bookExample = this.bookDAO.save(bookExample);
	this.books.add(bookExample);

	bookExample = new BookDO("Titulo5", this.createAuthorDTO(new AuthorDO().setName("NombreAutor2")));
	bookExample = this.bookDAO.save(bookExample);
	this.books.add(bookExample);

	PaginationSearchDTO<BookDO> paginationCriteria = new PaginationSearchDTO<BookDO>(
		new BookDO(), initialPosition, pageSize);

	/*
	 * Nos traemos la primera página de resultados y validamos que el
	 * resultado es correcto en cuanto a la paginación
	 */
	PaginationResultDTO<BookDO> firstPageResults = this.bookDAO
		.findBooksPaginated(paginationCriteria);

	Assert.assertNotNull("Result can't be null", firstPageResults);
	Assert.assertEquals("Initial position can't be modified",
		initialPosition, firstPageResults.getInitialPosition());
	Assert.assertEquals("Page size can't be modified", pageSize,
		firstPageResults.getPageSize());
	Assert.assertEquals("Total results have to be equals", this.books
		.size(), firstPageResults.getNumberOfResults().intValue());
	Assert.assertFalse("This is not the last page",
		firstPageResults.isLastPage());

	Assert.assertNotNull("Result can't be null",
		firstPageResults.getResult());
	Assert.assertFalse("Result can't be empty", firstPageResults
		.getResult().isEmpty());
	Assert.assertEquals("Result size is not correct", pageSize.intValue(),
		firstPageResults.getResult().size());

	/*
	 * Nos traemos la segunda página de resultados y validamos que el
	 * resultado es correcto en cuanto a la paginación
	 */
	initialPosition = initialPosition + 1;
	paginationCriteria = new PaginationSearchDTO<BookDO>(new BookDO(),
		initialPosition, pageSize);
	PaginationResultDTO<BookDO> secondPageResults = this.bookDAO
		.findBooksPaginated(paginationCriteria);

	Assert.assertNotNull("Result can't be null", secondPageResults);
	Assert.assertEquals("Initial position can't be modified",
		initialPosition, secondPageResults.getInitialPosition());
	Assert.assertEquals("Page size can't be modified", pageSize,
		secondPageResults.getPageSize());
	Assert.assertEquals("Total results have to be equals", this.books
		.size(), secondPageResults.getNumberOfResults().intValue());
	Assert.assertFalse("This is not the last page",
		secondPageResults.isLastPage());

	Assert.assertNotNull("Result can't be null",
		secondPageResults.getResult());
	Assert.assertFalse("Result can't be empty", secondPageResults
		.getResult().isEmpty());
	Assert.assertEquals("Result size is not correct", pageSize.intValue(),
		secondPageResults.getResult().size());

	/*
	 * Nos traemos la última página de resultados y validamos que el
	 * resultado es correcto en cuanto a la paginación
	 */
	initialPosition = initialPosition + 1;
	paginationCriteria = new PaginationSearchDTO<BookDO>(new BookDO(),
		initialPosition, pageSize);
	PaginationResultDTO<BookDO> lastPageResults = this.bookDAO
		.findBooksPaginated(paginationCriteria);

	Assert.assertNotNull("Result can't be null", lastPageResults);
	Assert.assertEquals("Initial position can't be modified",
		initialPosition, lastPageResults.getInitialPosition());
	Assert.assertEquals("Page size can't be modified", pageSize,
		lastPageResults.getPageSize());
	Assert.assertEquals("Total results have to be equals", this.books
		.size(), lastPageResults.getNumberOfResults().intValue());

	Assert.assertNotNull("Result can't be null",
		lastPageResults.getResult());
	Assert.assertFalse("Result can't be empty", lastPageResults.getResult()
		.isEmpty());
	Assert.assertTrue("This is the last page", lastPageResults.isLastPage());
	Assert.assertTrue("Result size is not correct",
		pageSize.intValue() >= lastPageResults.getResult().size());

	/* Comprobamos que los resultados parciales son correctos */
	Set<String> tmp = new HashSet<String>();
	int resultsCounter = 0;
	for (BookDO res : firstPageResults.getResult()) {

	    resultsCounter++;
	    tmp.add(res.getId());

	    Assert.assertEquals("Some result has been repeated",
		    resultsCounter, tmp.size());
	}

	for (BookDO res : secondPageResults.getResult()) {

	    resultsCounter++;
	    tmp.add(res.getId());

	    Assert.assertEquals(
		    "Some result of second search has been repeated",
		    resultsCounter, tmp.size());
	}

	for (BookDO res : lastPageResults.getResult()) {

	    resultsCounter++;
	    tmp.add(res.getId());

	    Assert.assertEquals("Some result of last search has been repeated",
		    resultsCounter, tmp.size());
	}
    }

    /**
     * <p>
     * Method that removes the data inserted for testing
     * </p>
     * 
     * @throws DALException
     */
    @After
    public void tearDown() throws DALException {

	log.info("Entering 'tearDown'");
	for (BookDO book : this.books) {

	    log.trace("Deleting book '" + book.getId() + "'");
	    this.bookDAO.delete(book);
	    log.trace("Book '" + book + "' deleted");
	}
	
	for (AuthorDO author: this.authors) {
	    
	    log.trace(String.format("Deleting author '%s'", author.getId()));
	    this.authorDAO.delete(author);
	    log.trace(String.format("Author '%s' deleted", author.getId()));
	}
    }
}
