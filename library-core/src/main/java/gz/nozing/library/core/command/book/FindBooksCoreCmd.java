package gz.nozing.library.core.command.book;

import gz.nozing.library.core.command.common.BaseCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.exception.DALException;

import java.util.List;

public class FindBooksCoreCmd extends BaseCoreCmd<List<BookDO>> {

	private BookDO bookCriteria;

	/**
	 * @param bookCriteria
	 */
	public FindBooksCoreCmd(BookDO bookCriteria) {
		super();
		this.bookCriteria = bookCriteria;
	}

	@Override
	protected List<BookDO> process() throws CoreException {

		BookDAO bookDAO = (BookDAO) this.getDAO(BookDAO.class);

		try {
			return bookDAO.find(this.bookCriteria);
		} catch (DALException e) {

			throw new CoreException("Error searching book", e);
		}
	}
}
