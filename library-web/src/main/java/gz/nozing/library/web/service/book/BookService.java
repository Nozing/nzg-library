package gz.nozing.library.web.service.book;

import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.core.exception.EntityNotFoundCoreException;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.web.controller.book.dto.BookWebDTO;
import gz.nozing.library.web.controller.book.form.InsertBookFormDTO;
import gz.nozing.library.web.controller.book.form.SearchBookFormDTO;
import gz.nozing.library.web.controller.book.form.UpdateBookFormDTO;
import gz.nozing.library.web.controller.form.PaginationFormResultDTO;

import java.util.Collection;

public interface BookService {

	BookDO findById(String id) throws CoreException;
	
	Collection<BookDO> findAll(Integer first, Integer last, Integer pageSize) throws CoreException;
	
	PaginationFormResultDTO<BookWebDTO> findBooksByFormCriteria(SearchBookFormDTO form) throws CoreException;
	
	BookWebDTO insertBook(InsertBookFormDTO form) throws CoreException;
	
	BookWebDTO updateBook(UpdateBookFormDTO updateForm) throws CoreException;
	
	void deleteBook(String id) throws EntityNotFoundCoreException, CoreException;
}
