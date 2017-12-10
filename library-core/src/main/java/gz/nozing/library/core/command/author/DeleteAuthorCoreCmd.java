/**
 * 
 */
package gz.nozing.library.core.command.author;

import gz.nozing.library.core.command.common.BaseCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.author.dao.AuthorDAO;
import gz.nozing.library.dal.exception.DALException;

/**
 * @author nozing
 *
 */
public class DeleteAuthorCoreCmd extends BaseCoreCmd<Void> {

	private String id;
	
	/**
	 * @param id
	 */
	public DeleteAuthorCoreCmd(String id) {

		this.id = id;
	}
	/* (non-Javadoc)
	 * @see gz.nozing.library.core.command.common.BaseCoreCmd#process()
	 */
	@Override
	protected Void process() throws CoreException {
		
		try {
			
			this.getDAO(AuthorDAO.class).delete(this.id);
			
		} catch (DALException e) {

			throw new CoreException(e.getMessage());
		}
		
		return null;
	}
}
