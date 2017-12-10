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
public class CreateNewAuthorCoreCmd extends BaseCoreCmd<AuthorDO> {

	private AuthorDO authorDO;

	public CreateNewAuthorCoreCmd(AuthorDO authorDO) {
		
		this.authorDO = authorDO;
	}
	
	@Override
	protected AuthorDO process() throws CoreException {

		try {

			return ((AuthorDAO) this.getDAO(AuthorDAO.class)).save(this.authorDO);

		} catch (DALException e) {

			throw new CoreException(
					String.format("Error saving author: %s",
					e.getMessage()), e);
		}
	}
}
