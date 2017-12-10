package gz.nozing.library.test.core.command;

import gz.nozing.library.core.command.book.CreateNewBookCoreCmd;
import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.util.DaoFactory;
import gz.nozing.library.test.core.util.TestCoreContextImpl;
import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

public class CreateNewBookCoreCmdTest {

	@SuppressWarnings("unused")
	private static Logger log = Logger
			.getLogger(CreateNewBookCoreCmdTest.class);

	private BookDAO bookDAO;

	public CreateNewBookCoreCmdTest() throws Exception {

		this.bookDAO = DaoFactory.getDAO(BookDAO.class);
		this.bookDAO.setDatabase(TestCoreContextImpl.instance().getDatabase());
	}

	@Test
	public void testCreateNewBook() throws Exception {

		BookDO newBook = new BookDO();

		newBook.addAuthor(new AuthorDTO("1", "Nombre autor"));
		newBook.setTitle("título libro");
		newBook.setNote("Una descripción cualquiera");

		CreateNewBookCoreCmd cnbcc = new CreateNewBookCoreCmd(newBook);
		cnbcc.setCoreContext(TestCoreContextImpl.instance());

		BookDO createdBookDO = cnbcc.execute();

		try {

			Assert.assertNotNull("Created book can't be null", createdBookDO);
			Assert.assertNotNull("Id can't be null", createdBookDO.getId());

			Assert.assertSame(
					"'title' of new book is not the same as the original one",
					newBook.getTitle(), createdBookDO.getTitle());
			
			Assert.assertSame(
					"'author' of new book is not the same as the original one",
					newBook.getAuthors(), createdBookDO.getAuthors());

		} finally {

			this.bookDAO.delete(createdBookDO);
		}
	}
}
