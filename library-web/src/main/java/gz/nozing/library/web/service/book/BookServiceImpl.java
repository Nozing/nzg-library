/**
 * 
 */
package gz.nozing.library.web.service.book;

import gz.nozing.library.common.utils.CommonUtils;
import gz.nozing.library.core.command.author.CreateNewAuthorCoreCmd;
import gz.nozing.library.core.command.book.CreateNewBookCoreCmd;
import gz.nozing.library.core.command.book.DeleteBookByIdCoreCmd;
import gz.nozing.library.core.command.book.FindBookByIdCoreCmd;
import gz.nozing.library.core.command.book.FindBooksPaginatedCoreCmd;
import gz.nozing.library.core.command.book.ModifyBookCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.author.AuthorDO;
import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.dal.common.pagination.PaginationResultDTO;
import gz.nozing.library.dal.common.pagination.PaginationSearchDTO;
import gz.nozing.library.web.controller.book.dto.AuthorBookWebDTO;
import gz.nozing.library.web.controller.book.dto.BookWebDTO;
import gz.nozing.library.web.controller.book.form.InsertBookAuthorFormDTO;
import gz.nozing.library.web.controller.book.form.InsertBookFormDTO;
import gz.nozing.library.web.controller.book.form.SearchBookFormDTO;
import gz.nozing.library.web.controller.book.form.UpdateBookFormDTO;
import gz.nozing.library.web.controller.form.PaginationFormResultDTO;
import gz.nozing.library.web.service.BaseServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author nozing
 *
 */
@Service
public class BookServiceImpl extends BaseServiceImpl implements BookService {

	private static Logger log = Logger.getLogger(BookServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see gz.nozing.library.web.service.book.BookService#findAll(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Collection<BookDO> findAll(Integer first, Integer last,
			Integer pageSize) throws CoreException {
		
		throw new UnsupportedOperationException("Method 'findAll' not implemented");
	}

	/* (non-Javadoc)
	 * @see gz.nozing.library.web.service.book.BookService#findById(java.lang.String)
	 */
	@Override
	public BookDO findById(String id) throws CoreException {
		
		log.debug("Entering 'findById'");
				
		FindBookByIdCoreCmd cmd = new FindBookByIdCoreCmd(id);
		cmd.setCoreContext(this.getCoreContext());
		
		return cmd.execute();
	}

	/* (non-Javadoc)
	 * @see gz.nozing.library.web.service.book.BookService#insertBook(gz.nozing.library.web.controller.form.book.InsertBookFormDTO)
	 */
	@Override
	public BookWebDTO insertBook(InsertBookFormDTO form) throws CoreException {
				
		CreateNewBookCoreCmd createCmd =
				new CreateNewBookCoreCmd(form.convertTo(this.getCoreContext()));
		createCmd.setCoreContext(this.getCoreContext());
		
		BookDO bookCreated = createCmd.execute();
		
		BookWebDTO result = BookWebDTO.buildFrom(bookCreated);
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see gz.nozing.library.web.service.book.BookService#findBooksByFormCriteria(gz.nozing.library.web.controller.form.book.SearchBookFormDTO)
	 */
	@Override
	public PaginationFormResultDTO<BookWebDTO> findBooksByFormCriteria(SearchBookFormDTO form)
		throws CoreException {
		
		log.debug("Entering 'findBooksByFormCriteria'");
		
		FindBooksPaginatedCoreCmd findBooksCmd = new FindBooksPaginatedCoreCmd(
				new PaginationSearchDTO<BookDO>(form.convertTO(), form.getInitial(), form.getPageSize()));
		findBooksCmd.setCoreContext(this.getCoreContext());
		
		PaginationResultDTO<BookDO> result = findBooksCmd.execute();
		
		Collection<BookWebDTO> searchResult = new LinkedList<BookWebDTO>();
		
		for (BookDO book : result.getResult()) {
			
			log.debug(String.format("Book: %s", book));
			searchResult.add(BookWebDTO.buildFrom(book));
		}
		
		log.debug(String.format(
				"Exiting 'findBooksByFormCriteria': Number of results = %s - " +
				"Initial position = %s - Page size = %s - Total = %s",
				searchResult.size(), result.getInitialPosition(), 
				result.getPageSize(), result.getNumberOfResults()));
		
		PaginationFormResultDTO<BookWebDTO> paginationFormResult = 
			new PaginationFormResultDTO<BookWebDTO>(
				result.getNumberOfResults(), result.getInitialPosition(),
				result.getPageSize());
		paginationFormResult.setResult(searchResult);
		
		return paginationFormResult;
	}
	
	/* (non-Javadoc)
	 * @see gz.nozing.library.web.service.book.BookService#deleteBook(java.lang.String)
	 */
	@Override
	public void deleteBook(String id) throws CoreException {

		DeleteBookByIdCoreCmd deleteCmd = new DeleteBookByIdCoreCmd(id);
		deleteCmd.setCoreContext(this.getCoreContext());
		
		deleteCmd.execute();
	}

	@Override
	public BookWebDTO updateBook(UpdateBookFormDTO updateForm)
			throws CoreException {
		
		ModifyBookCoreCmd modifyCmd = new ModifyBookCoreCmd(
				updateForm.convertTo(this.getCoreContext()));
		modifyCmd.setCoreContext(this.getCoreContext());
				
		return BookWebDTO.buildFrom(modifyCmd.execute());
	}
}
