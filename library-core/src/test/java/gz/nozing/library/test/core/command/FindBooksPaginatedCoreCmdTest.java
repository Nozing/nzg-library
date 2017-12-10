package gz.nozing.library.test.core.command;

import gz.nozing.library.common.utils.CommonUtils;
import gz.nozing.library.core.command.book.FindBooksPaginatedCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.common.pagination.PaginationResultDTO;
import gz.nozing.library.dal.common.pagination.PaginationSearchDTO;
import gz.nozing.library.dal.util.DaoFactory;
import gz.nozing.library.test.core.util.TestCoreContextImpl;

import java.util.Collection;
import java.util.LinkedList;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FindBooksPaginatedCoreCmdTest {

	private static Logger log = Logger
			.getLogger(FindBooksPaginatedCoreCmdTest.class);

	private Collection<BookDO> testBooks;
	private BookDAO bookDAO;

	public FindBooksPaginatedCoreCmdTest() throws Exception {

		this.bookDAO = DaoFactory.getDAO(BookDAO.class);
		this.bookDAO.setDatabase(TestCoreContextImpl.instance().getDatabase());
	}

	@SuppressWarnings("serial")
	@Before
	public void loadTestData() throws Exception {

		this.testBooks = new LinkedList<BookDO>() {
			{

				add(new BookDO("titulo", new AuthorDTO("1", "author1")));
				add(new BookDO("titulo", new AuthorDTO("1", "author2")));
				add(new BookDO("titulo", new AuthorDTO("1", "author3")));
				add(new BookDO("titulo", new AuthorDTO("1", "author4")));
				add(new BookDO("titulo", new AuthorDTO("1", "author5")));
				add(new BookDO("titulo", new AuthorDTO("1", "author6")));
				add(new BookDO("titulo", new AuthorDTO("1", "author7")));
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

		Integer actualPage = 0;
		Integer pageSize = 2;

		/* Probamos el funcionamiento de una búsqueda correcta */
		FindBooksPaginatedCoreCmd fbpcc = new FindBooksPaginatedCoreCmd(
				new PaginationSearchDTO<BookDO>(bookCriteria, actualPage, pageSize));
		fbpcc.setCoreContext(TestCoreContextImpl.instance());

		PaginationResultDTO<BookDO> books = null;
		try {

			books = fbpcc.execute();

		} catch (CoreException e) {

			Assert.fail("Error searching book: ''" + bookCriteria + "' -> "
					+ e.getMessage());
		}
		
		Assert.assertNotNull("Result can't be null", books);
		Assert.assertFalse("Result can't be empty",
				CommonUtils.isEmpty(books.getResult()));
		Assert.assertEquals("total number of results is not correct", 
				pageSize.intValue(), books.getResult().size());
	}

	/**
	 * 
	 */
	@Test
	public void findBooksPaginatedCoreCmdNoResultTest() {

		BookDO bookCriteria = new BookDO();
		bookCriteria.setTitle("tituloNoExistente");

		Integer actualPage = 0;
		Integer pageSize = 2;

		/* Probamos el funcionamiento de una búsqueda correcta */
		FindBooksPaginatedCoreCmd fbpcc = new FindBooksPaginatedCoreCmd(
				new PaginationSearchDTO<BookDO>(bookCriteria, actualPage, pageSize));
		fbpcc.setCoreContext(TestCoreContextImpl.instance());

		try {

			PaginationResultDTO<BookDO> books = fbpcc.execute();

			Assert.assertNotNull("Result can't be null", books);
			Assert.assertTrue("Result must be empty",
					CommonUtils.isEmpty(books.getResult()));

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
