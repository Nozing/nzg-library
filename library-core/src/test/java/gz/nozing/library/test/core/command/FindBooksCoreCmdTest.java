package gz.nozing.library.test.core.command;

import gz.nozing.library.common.utils.CommonUtils;
import gz.nozing.library.core.command.book.FindBooksCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.util.DaoFactory;
import gz.nozing.library.test.core.util.TestCoreContextImpl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FindBooksCoreCmdTest {

	private static Logger log = Logger.getLogger(FindBooksCoreCmdTest.class);

	private Collection<BookDO> testBooks;
	private BookDAO bookDAO;

	public FindBooksCoreCmdTest() throws Exception {

		this.bookDAO = DaoFactory.getDAO(BookDAO.class);
		this.bookDAO.setDatabase(TestCoreContextImpl.instance().getDatabase());
	}

	@SuppressWarnings("serial")
	@Before
	public void loadTestData() throws Exception {

		this.testBooks = new LinkedList<BookDO>() {
			{

				add(new BookDO("titulo", new AuthorDTO("author1")));
				add(new BookDO("titulo", new AuthorDTO("author2")));
				add(new BookDO("titulo", new AuthorDTO("author3")));
				add(new BookDO("titulo", new AuthorDTO("author4")));
				add(new BookDO("titulo", new AuthorDTO("author5")));
				add(new BookDO("titulo", new AuthorDTO("author6")));
				add(new BookDO("titulo", new AuthorDTO("author7")));
			}
		};

		for (BookDO book : this.testBooks) {

			this.bookDAO.save(book);
		}
	}

	@Test
	public void findBooksPaginatedCoreCmdTest() {

		BookDO bookCriteria = new BookDO();
		bookCriteria.setTitle("titulo");

		/* Probamos el funcionamiento de una búsqueda correcta */
		FindBooksCoreCmd fbpcc = new FindBooksCoreCmd(bookCriteria);
		fbpcc.setCoreContext(TestCoreContextImpl.instance());

		try {

			List<BookDO> books = fbpcc.execute();

			Assert.assertNotNull("Result can't be null", books);
			Assert.assertFalse("Result can't be empty",
					CommonUtils.isEmpty(books));

		} catch (CoreException e) {

			Assert.fail("Error searching book: ''" + bookCriteria + "' -> "
					+ e.getMessage());
		}
	}

	@Test
	public void findBooksCoreCmdNoResultTest() {

		BookDO bookCriteria = new BookDO();
		bookCriteria.setTitle("tituloNoExistente");

		FindBooksCoreCmd fbpcc = new FindBooksCoreCmd(bookCriteria);
		fbpcc.setCoreContext(TestCoreContextImpl.instance());

		try {

			List<BookDO> books = fbpcc.execute();

			Assert.assertNotNull("Result can't be null", books);
			Assert.assertTrue("Result must be empty",
					CommonUtils.isEmpty(books));

		} catch (CoreException e) {

			Assert.fail("Error searching book: ''" + bookCriteria + "' -> "
					+ e.getMessage());
		}
	}

	@After
	public void removeTestData() throws Exception {

		log.info("Entering 'removeTestData'");
		for (BookDO book : this.testBooks) {

			log.info("Removing book '" + book.getId() + "'");
			this.bookDAO.delete(book);
			log.info("Book '" + book.getId() + "' removed");
		}
	}
}
