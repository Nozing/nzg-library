package gz.nozing.library.core.command.book;

import gz.nozing.library.core.command.common.BaseCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.exception.DALException;

public class ModifyBookCoreCmd extends BaseCoreCmd<BookDO> {

	private BookDO modifiedBook;

	public ModifyBookCoreCmd(BookDO bookToModify) {

		this.modifiedBook = bookToModify;
	}

	@Override
	protected BookDO process() throws CoreException {

		BookDAO bookDAO = (BookDAO) this.getDAO(BookDAO.class);

		try {

			return bookDAO.update(this.modifiedBook);

		} catch (DALException e) {

			throw new CoreException("Error updating book '"
					+ this.modifiedBook.getId() + "'", e);
		}
	}
}
