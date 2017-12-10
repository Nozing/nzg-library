/**
 * 
 */
package gz.nozing.library.core.command.book;

import gz.nozing.library.core.command.common.BaseCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.core.exception.EntityNotFoundCoreException;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.exception.DALException;
import gz.nozing.library.dal.exception.EntityNotFoundException;

import org.apache.log4j.Logger;

/**
 * <p>
 * Comando que borra un libro en función de su identificador
 * </p>
 * 
 * @author nozing
 * 
 */
public class DeleteBookByIdCoreCmd extends BaseCoreCmd<Void> {

	private static Logger log = Logger.getLogger(DeleteBookByIdCoreCmd.class);

	private String bookId;

	/**
	 * @param bookId
	 */
	public DeleteBookByIdCoreCmd(String bookId) {
		super();
		this.bookId = bookId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gz.nozing.library.core.command.common.BaseCoreCmd#process()
	 */
	@Override
	protected Void process() throws CoreException {

		log.debug("Entering 'process'");
		log.trace(String.format("Deleting book id '%s'", this.bookId));

		BookDAO bookDAO = (BookDAO) this.getDAO(BookDAO.class);

		try {

			bookDAO.delete(this.bookId);

		} catch (EntityNotFoundException enfe) {

			throw new EntityNotFoundCoreException(
				String.format("No book with id '%s' found", this.bookId), 
				enfe);
		} catch (DALException e) {

			throw new CoreException(String.format(
					"Error deleting book by id '%s'", this.bookId), e);
		}

		return null;
	}
}
