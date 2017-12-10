/**
 * 
 */
package gz.nozing.library.core.command.book;

import gz.nozing.library.core.command.common.BaseCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.book.dao.BookDAO;
import gz.nozing.library.dal.exception.DALException;

import org.apache.log4j.Logger;

/**
 * @author nozing
 *
 */
public class DeleteBookCoreCmd extends BaseCoreCmd<Void> {

    private static Logger log = Logger.getLogger(DeleteBookCoreCmd.class);

    private BookDO bookToDelete;

    /**
     * @param bookToDelete
     */
    public DeleteBookCoreCmd(BookDO bookToDelete) {
	super();
	this.bookToDelete = bookToDelete;
    }

    /* (non-Javadoc)
     * @see gz.nozing.library.core.command.common.BaseCoreCmd#process()
     */
    @Override
    protected Void process() throws CoreException {

	log.debug("Entering 'process'");
	BookDAO bookDAO = (BookDAO) this.getDAO(BookDAO.class);

	try {

	    bookDAO.delete(this.bookToDelete);

	} catch (DALException e) {

	    throw new CoreException("Error searching book", e);
	}

	return null;
    }

}
