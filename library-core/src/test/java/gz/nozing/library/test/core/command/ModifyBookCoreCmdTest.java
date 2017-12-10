package gz.nozing.library.test.core.command;

import gz.nozing.library.core.command.book.ModifyBookCoreCmd;
import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.util.DaoFactory;
import gz.nozing.library.test.core.util.TestCoreContextImpl;

import java.util.Date;
import java.util.LinkedList;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ModifyBookCoreCmdTest {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(ModifyBookCoreCmdTest.class);

	private BookDAO bookDAO;

	public ModifyBookCoreCmdTest() throws Exception {

		this.bookDAO = DaoFactory.getDAO(BookDAO.class);
		this.bookDAO.setDatabase(TestCoreContextImpl.instance().getDatabase());
	}

	@Test
	public void testModifyAuthorOfExistingBook() throws Exception {

		BookDO newBook = new BookDO();

		newBook.addAuthor(new AuthorDTO("Author 1"));
		newBook.setTitle("título libro");
		newBook.setNote("Una descripción cualquiera");

		try {

			newBook = this.bookDAO.save(newBook);

			AuthorDTO newAuthor = new AuthorDTO("Author 2");
			
			newBook.setAuthors(new LinkedList<AuthorDTO>());
			newBook.addAuthor(newAuthor);

			ModifyBookCoreCmd mbcc = new ModifyBookCoreCmd(newBook);
			mbcc.setCoreContext(TestCoreContextImpl.instance());

			Thread.sleep(2000);
			Date newBookLastModificationDate = newBook.getLastModificationDate();
			BookDO modifiedBook = mbcc.execute();

			Assert.assertNotNull("Created book can't be null. ", modifiedBook);

			Assert.assertNotNull("Id can't be null. ", modifiedBook.getId());
			Assert.assertEquals("'id' has been updated. ", newBook.getId(),
					modifiedBook.getId());

			Assert.assertEquals("'title' has been modified. ",
					newBook.getTitle(), modifiedBook.getTitle());
			Assert.assertEquals("'note' has been modified", 
					newBook.getNote(), modifiedBook.getNote());
			Assert.assertEquals("'creationDate' has been modified", 
					newBook.getCreationDate(), modifiedBook.getCreationDate());
			Assert.assertTrue("'modificationDate' of modified book has not been updated", 
					newBookLastModificationDate.before(modifiedBook.getLastModificationDate()));
			
			Assert.assertEquals("Number of 'authors' is incorrect", 
					1, modifiedBook.getAuthors().size());

			Assert.assertEquals("'authors' has not been correctly modified", newAuthor, modifiedBook.getAuthors().get(0));

		} finally {

			this.bookDAO.delete(newBook);
		}
	}
}
