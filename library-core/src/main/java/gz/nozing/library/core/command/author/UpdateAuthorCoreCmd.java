/**
 * 
 */
package gz.nozing.library.core.command.author;

import gz.nozing.library.core.command.common.BaseCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.author.AuthorDO;
import gz.nozing.library.dal.author.dao.AuthorDAO;
import gz.nozing.library.dal.exception.DALException;

/**
 * @author nozing
 *
 */
public class UpdateAuthorCoreCmd extends BaseCoreCmd<AuthorDO> {

	private AuthorDO author;
	
	/**
	 * @param author
	 */
	public UpdateAuthorCoreCmd(AuthorDO author) {
		super();
		this.author = author;
	}

	/* (non-Javadoc)
	 * @see gz.nozing.library.core.command.common.BaseCoreCmd#process()
	 */
	@Override
	protected AuthorDO process() throws CoreException {
		
		try {
		
			return ((AuthorDAO) this.getDAO(AuthorDAO.class))
					.update(this.author);

		} catch (DALException e) {

			throw new CoreException(
					String.format("Error updating author '%s'",
					this.author.getId()), e);
		}
	}
}
