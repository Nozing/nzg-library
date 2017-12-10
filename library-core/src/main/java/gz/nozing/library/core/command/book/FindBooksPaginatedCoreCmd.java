package gz.nozing.library.core.command.book;

import gz.nozing.library.core.command.common.BaseCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.common.pagination.PaginationResultDTO;
import gz.nozing.library.dal.common.pagination.PaginationSearchDTO;
import gz.nozing.library.dal.exception.DALException;

public class FindBooksPaginatedCoreCmd extends
		BaseCoreCmd<PaginationResultDTO<BookDO>> {

	private PaginationSearchDTO<BookDO> paginationCriteria;

	/**
	 * @param paginationCriteria
	 */
	public FindBooksPaginatedCoreCmd(PaginationSearchDTO<BookDO> paginationCriteria) {
		super();
		
		this.paginationCriteria = paginationCriteria;
	}

	@Override
	protected PaginationResultDTO<BookDO> process()
			throws CoreException {

		BookDAO bookDAO = (BookDAO) this.getDAO(BookDAO.class);

		try {
			
			return bookDAO.findBooksPaginated(this.paginationCriteria);
		} catch (DALException e) {

			throw new CoreException("Error searching book", e);
		}
	}
}
