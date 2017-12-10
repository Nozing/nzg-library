/**
 * 
 */
package gz.nozing.library.web.service.author;

import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.web.controller.author.dto.AuthorWebDTO;
import gz.nozing.library.web.controller.author.form.CreateAuthorFormDTO;
import gz.nozing.library.web.controller.author.form.SearchAuthorsFormDTO;
import gz.nozing.library.web.controller.author.form.UpdateAuthorFormDTO;
import gz.nozing.library.web.controller.form.PaginationFormResultDTO;

/**
 * @author nozing
 *
 */
public interface AuthorService {

	/**
	 * @param authorForm
	 * @return
	 * @throws CoreException
	 */
	AuthorWebDTO createAuthor(
			CreateAuthorFormDTO authorForm) throws CoreException;
	
	/**
	 * @param authorForm
	 * @return
	 * @throws CoreException
	 */
	AuthorWebDTO updateAuthor(
			UpdateAuthorFormDTO authorForm) throws CoreException;
	
	/**
	 * @param id
	 * @throws CoreException
	 */
	void deleteAuthor(String id) throws CoreException;
	
	/**
	 * @param authorsForm
	 * @return
	 * @throws CoreException
	 */
	PaginationFormResultDTO<AuthorWebDTO> searchAuthors(
			SearchAuthorsFormDTO authorsForm)
		throws CoreException;
}
