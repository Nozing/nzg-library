/**
 * 
 */
package gz.nozing.library.core.command.author;

import gz.nozing.library.core.command.common.BaseCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.author.AuthorDO;
import gz.nozing.library.dal.author.dao.AuthorDAO;
import gz.nozing.library.dal.common.pagination.PaginationResultDTO;
import gz.nozing.library.dal.common.pagination.PaginationSearchDTO;
import gz.nozing.library.dal.exception.DALException;

/**
 * @author nozing
 *
 */
public class SearchAuthorsCoreCmd extends BaseCoreCmd<PaginationResultDTO<AuthorDO>> {

	private PaginationSearchDTO<AuthorDO> paginatedCriteria;
	
	/**
	 * @param paginatedCriteria
	 */
	public SearchAuthorsCoreCmd(PaginationSearchDTO<AuthorDO> paginatedCriteria) {
		super();
		this.paginatedCriteria = paginatedCriteria;
	}

	/* (non-Javadoc)
	 * @see gz.nozing.library.core.command.common.BaseCoreCmd#process()
	 */
	@Override
	protected PaginationResultDTO<AuthorDO> process() throws CoreException {

		try {
			
			return ((AuthorDAO) this.getDAO(AuthorDAO.class))
					.findByCriteria(this.paginatedCriteria);
		
		} catch (DALException dale) {

			throw new CoreException("Error searching authors", dale);
		}
	}

}
