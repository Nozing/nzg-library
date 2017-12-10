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
public class FindBookByIdCoreCmd extends BaseCoreCmd<BookDO> {

	private String bookId;

	/**
	 * @param id
	 */
	public FindBookByIdCoreCmd(String id) {
		super();
		this.bookId = id;
	}

	/* (non-Javadoc)
	 * @see gz.nozing.library.core.command.common.BaseCoreCmd#process()
	 */
	@Override
	protected BookDO process() throws CoreException {

		BookDAO bookDAO = (BookDAO) this.getDAO(BookDAO.class);

		try {
			return bookDAO.findEntityById(this.bookId);
			
		} catch (DALException e) {

			throw new CoreException("Error searching book", e);
		}
	}
}
