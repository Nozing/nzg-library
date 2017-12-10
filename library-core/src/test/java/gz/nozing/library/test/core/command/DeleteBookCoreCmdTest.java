/**
 * 
 */
package gz.nozing.library.test.core.command;

import gz.nozing.library.core.command.book.DeleteBookCoreCmd;
import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.exception.EntityNotFoundException;
import gz.nozing.library.dal.util.DaoFactory;
import gz.nozing.library.test.core.util.TestCoreContextImpl;
import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author nozing
 * 
 */
public class DeleteBookCoreCmdTest {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(DeleteBookCoreCmdTest.class);

	private BookDAO bookDAO;

	public DeleteBookCoreCmdTest() throws Exception {

		this.bookDAO = DaoFactory.getDAO(BookDAO.class);
		this.bookDAO.setDatabase(TestCoreContextImpl.instance().getDatabase());
	}

	@Test
	public void testDeleteExistingBook() throws Exception {

		BookDO newBook = new BookDO();

		newBook.addAuthor(new AuthorDTO("nombre author"));
		newBook.setTitle("título libro");
		newBook.setNote("Una descripción cualquiera");

		newBook = this.bookDAO.save(newBook);

		DeleteBookCoreCmd dbcc = new DeleteBookCoreCmd(newBook);
		dbcc.setCoreContext(TestCoreContextImpl.instance());

		try {

			dbcc.execute();

			BookDO noBook = null;
			try {

				noBook = this.bookDAO.findEntityById(newBook.getId());

			} catch (Exception exc) {

				Assert.assertTrue("Exception thrown not expected: " + exc,
						exc instanceof EntityNotFoundException);
			}

			Assert.assertNull("Book has not been deleted from database", noBook);

		} finally {

			this.bookDAO.delete(newBook);
		}
	}

	@Test
	public void testDeleteNotExistingBook() throws Exception {

		BookDO newBook = new BookDO();

		newBook.addAuthor(new AuthorDTO("nombre author"));
		newBook.setTitle("título libro");
		newBook.setNote("Una descripción cualquiera");

		newBook = this.bookDAO.save(newBook);

		this.bookDAO.delete(newBook);

		DeleteBookCoreCmd dbcc = new DeleteBookCoreCmd(newBook);
		dbcc.setCoreContext(TestCoreContextImpl.instance());

		try {

			dbcc.execute();

			BookDO noBook = null;
			try {

				noBook = this.bookDAO.findEntityById(newBook.getId());

			} catch (Exception exc) {

				Assert.assertTrue("Exception thrown not expected: " + exc,
						exc instanceof EntityNotFoundException);
			}

			Assert.assertNull("Book has not been deleted from database", noBook);

		} finally {

			this.bookDAO.delete(newBook);
		}
	}
}
