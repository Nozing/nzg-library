/**
 * 
 */
package gz.nozing.library.core.command.book;

import gz.nozing.library.core.command.common.BaseCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.exception.DALException;

/**
 * @author nozing
 * 
 */
public class CreateNewBookCoreCmd extends BaseCoreCmd<BookDO> {

	private BookDO newBook;

	/**
	 * @param newBook
	 */
	public CreateNewBookCoreCmd(BookDO newBook) {
		super();
		this.newBook = newBook;
	}

	@Override
	protected BookDO process() throws CoreException {

		BookDAO bookDAO = (BookDAO) this.getDAO(BookDAO.class);

		try {

			return bookDAO.save(this.newBook);

		} catch (DALException e) {

			throw new CoreException("Error saving book", e);
		}
	}
}
