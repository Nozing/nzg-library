package gz.nozing.library.web.service.author;

import gz.nozing.library.core.command.author.CreateNewAuthorCoreCmd;
import gz.nozing.library.core.command.author.DeleteAuthorCoreCmd;
import gz.nozing.library.core.command.author.SearchAuthorsCoreCmd;
import gz.nozing.library.core.command.author.UpdateAuthorCoreCmd;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.author.AuthorDO;
import gz.nozing.library.dal.common.pagination.PaginationResultDTO;
import gz.nozing.library.dal.common.pagination.PaginationSearchDTO;
import gz.nozing.library.web.controller.author.dto.AuthorWebDTO;
import gz.nozing.library.web.controller.author.form.CreateAuthorFormDTO;
import gz.nozing.library.web.controller.author.form.SearchAuthorsFormDTO;
import gz.nozing.library.web.controller.author.form.UpdateAuthorFormDTO;
import gz.nozing.library.web.controller.form.PaginationFormResultDTO;
import gz.nozing.library.web.service.BaseServiceImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * @author nozing
 *
 */
public class AuthorServiceImpl extends BaseServiceImpl implements AuthorService  {

	/* (non-Javadoc)
	 * @see gz.nozing.library.web.service.author.AuthorService#createAuthor(gz.nozing.library.web.controller.author.form.CreateAuthorFormDTO)
	 */
	@Override
	public AuthorWebDTO createAuthor(CreateAuthorFormDTO authorForm)
			throws CoreException {
		
		AuthorDO authorDO = new AuthorDO();
		authorDO.setName(authorForm.getName());
		authorDO.setSurname(authorForm.getSurname());
		
		CreateNewAuthorCoreCmd createAuthorCmd = 
				new CreateNewAuthorCoreCmd(authorDO);
		createAuthorCmd.setCoreContext(this.getCoreContext());
		
		authorDO = createAuthorCmd.execute();
		
		AuthorWebDTO authorWeb = new AuthorWebDTO();
		authorWeb.setAuthorId(authorDO.getId());
		authorWeb.setName(authorDO.getName());
		authorWeb.setSurname(authorDO.getSurname());
		authorWeb.setCreationDate(authorDO.getCreationDate());
		authorWeb.setModificationDate(authorDO.getModificationDate());
		
		return authorWeb;
	}

	/* (non-Javadoc)
	 * @see gz.nozing.library.web.service.author.AuthorService#deleteAuthor(java.lang.String)
	 */
	@Override
	public void deleteAuthor(String id) throws CoreException {
		
		DeleteAuthorCoreCmd deleteCmd = 
				new DeleteAuthorCoreCmd(id);
		deleteCmd.setCoreContext(this.getCoreContext());
		
		deleteCmd.execute();		
	}

	@Override
	public AuthorWebDTO updateAuthor(UpdateAuthorFormDTO authorForm)
			throws CoreException {
		
		AuthorDO author = new AuthorDO();
		author.setId(authorForm.getId());
		author.setName(authorForm.getName());
		author.setSurname(authorForm.getSurname());
		
		UpdateAuthorCoreCmd updateCmd = 
				new UpdateAuthorCoreCmd(author);
		updateCmd.setCoreContext(this.getCoreContext());
		
		author = updateCmd.execute();
		
		AuthorWebDTO result = new AuthorWebDTO();
		result.setAuthorId(author.getId());
		result.setName(author.getName());
		result.setSurname(author.getSurname());
		result.setCreationDate(author.getCreationDate());
		result.setModificationDate(author.getModificationDate());
		
		return result;
	}

	/* (non-Javadoc)
	 * @see gz.nozing.library.web.service.author.AuthorService#searchAuthors(gz.nozing.library.web.controller.form.PaginationFormDTO)
	 */
	@Override
	public PaginationFormResultDTO<AuthorWebDTO> searchAuthors(
			SearchAuthorsFormDTO authorsForm)
		throws CoreException {
		
		AuthorDO authorCriteria = new AuthorDO();
		authorCriteria.setName(authorsForm.getName());
		authorCriteria.setSurname(authorsForm.getSurname());

		PaginationSearchDTO<AuthorDO> criteria = new PaginationSearchDTO<AuthorDO>(
				authorCriteria, authorsForm.getPageSize(), authorsForm.getInitial());
						
		SearchAuthorsCoreCmd searchAuthorsCmd = 
				new SearchAuthorsCoreCmd(criteria);
		searchAuthorsCmd.setCoreContext(this.getCoreContext());
		
		List<AuthorWebDTO> result = new LinkedList<AuthorWebDTO>();
		
		PaginationResultDTO<AuthorDO> modelResult = searchAuthorsCmd.execute();
		for (AuthorDO author : modelResult.getResult()) {
			
			AuthorWebDTO authorWeb = new AuthorWebDTO();
			authorWeb.setAuthorId(author.getId());
			authorWeb.setName(author.getName());
			authorWeb.setSurname(author.getSurname());
			authorWeb.setCreationDate(author.getCreationDate());
			authorWeb.setModificationDate(author.getModificationDate());
			
			result.add(authorWeb);
		}
		
		PaginationFormResultDTO<AuthorWebDTO> paginationResult = 
				new PaginationFormResultDTO<AuthorWebDTO>(
					modelResult.getNumberOfResults(), modelResult.getPageSize(),
					modelResult.getInitialPosition());
		paginationResult.setResult(result);
		
		return paginationResult;
	}	
}
