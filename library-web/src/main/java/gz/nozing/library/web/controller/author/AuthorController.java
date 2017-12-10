/**
 * 
 */
package gz.nozing.library.web.controller.author;

import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.web.controller.author.dto.AuthorWebDTO;
import gz.nozing.library.web.controller.author.form.CreateAuthorFormDTO;
import gz.nozing.library.web.controller.author.form.SearchAuthorsFormDTO;
import gz.nozing.library.web.controller.form.PaginationFormResultDTO;
import gz.nozing.library.web.service.author.AuthorService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author nozing
 *
 */
@Controller(value="authorController")
@RequestMapping("/author/")
public class AuthorController {

	private static Logger log = Logger.getLogger(AuthorController.class);
	
	public static final String HOME_CREATE_MAPPING = "createAuhtor.htm";
	public static final String CREATE_AUTHOR_ACTION_MAPPING = "createAuhtorAction.htm";
	public static final String SEARCH_AUTHOR_PAGE_MAPPING = "searchAuthors.htm";
	public static final String SEARCH_AUTHORS_ACTION_MAPPING = "searchAuthorsAction.htm";
	
	public static final String UPDATE_AUTHOR_ACTION_MAPPING = "updateAuthorAction.htm";
	
	public static final String REMOVE_AUTHOR_ACTION_MAPPING = "removeAuthorAction.htm";
	
	public static final String VIEW_AUTHOR_DETAILS_ACTION_MAPPING = "viewAuhtorDetails.htm";
	
	@Autowired
	private AuthorService authorService;
	
	/**
	 * @return
	 */
	@RequestMapping(value = HOME_CREATE_MAPPING, method = RequestMethod.GET)
	public ModelAndView startPage() {
 	
		log.trace("Entering 'startPage'");
		ModelAndView searchFormPage = 
				new ModelAndView("author.createForm");
		
		searchFormPage.addObject("createForm", new CreateAuthorFormDTO());
		
		return searchFormPage;
	}
	
	/**
	 * @param form
	 * @return
	 * @throws CoreException
	 */
	@RequestMapping(value = CREATE_AUTHOR_ACTION_MAPPING, method = RequestMethod.POST)
	public ModelAndView createAuthor(CreateAuthorFormDTO form) throws CoreException {
		
		ModelAndView createAuthorFormPage = 
//				new ModelAndView(new RedirectView(HOME_CREATE_MAPPING));
				new ModelAndView("author.createForm");
		
		createAuthorFormPage.addObject("createForm", form);
		createAuthorFormPage.addObject("result", this.authorService.createAuthor(form));
		
		return createAuthorFormPage;		
	}
		
	@RequestMapping(value = SEARCH_AUTHOR_PAGE_MAPPING, method = RequestMethod.GET)
	public ModelAndView setupSearchForm() throws CoreException {
		
		ModelAndView searchFormPage = 
				new ModelAndView("author.searchForm");
		
		searchFormPage.addObject("searchForm", new SearchAuthorsFormDTO());
		
		return searchFormPage;
	}
	
	@RequestMapping(value = SEARCH_AUTHORS_ACTION_MAPPING, method = RequestMethod.POST)
	public ModelAndView searchAuthors(@ModelAttribute SearchAuthorsFormDTO searchForm) throws CoreException {
		
		ModelAndView searchFormPage = 
//				new ModelAndView(new RedirectView(SEARCH_AUTHOR_PAGE_MAPPING));
				new ModelAndView("author.searchForm");
	
		searchFormPage.addObject("searchForm", searchForm);
		searchFormPage.addObject("searchResult", 
				this.authorService.searchAuthors(searchForm));
		
		return searchFormPage;
	}
	
	/**
	 * @param name
	 * @param surname
	 * @return
	 * @throws CoreException
	 */
//	@RequestMapping(value = SEARCH_AUTHORS_ACTION_MAPPING, method = RequestMethod.GET)
//	public @ResponseBody PaginationFormResultDTO<AuthorWebDTO> searchAuthors(
//			@RequestParam(value="name", required=false) String name, 
//			@RequestParam(value="surname", required=false) String surname) throws CoreException {
//		
//		SearchAuthorsFormDTO searchAuthorForm = 
//				new SearchAuthorsFormDTO();
//		searchAuthorForm.setName(name);
//		searchAuthorForm.setSurname(surname);
//		
//		PaginationFormSearchDTO<SearchAuthorsFormDTO> searchForm = 
//				new PaginationFormSearchDTO<SearchAuthorsFormDTO>(0, 15, searchAuthorForm); 
//		
//		PaginationFormResultDTO<AuthorWebDTO> searchResult = 
//				this.authorService.searchAuthors(searchForm);
//		
//		log.info(searchResult.getTotal());
//		return searchResult;
//	}
	
	@ResponseBody
	@RequestMapping(value = SEARCH_AUTHORS_ACTION_MAPPING, method = RequestMethod.GET)
	public PaginationFormResultDTO<AuthorWebDTO> searchAuthors(
			@RequestParam(value="name", required=false) String name, 
			@RequestParam(value="surname", required=false) String surname) throws CoreException {
		
		SearchAuthorsFormDTO searchAuthorForm = 
				new SearchAuthorsFormDTO();
		searchAuthorForm.setName(name);
		searchAuthorForm.setSurname(surname);
		
		PaginationFormResultDTO<AuthorWebDTO> searchResult = 
				this.authorService.searchAuthors(searchAuthorForm);
		
		log.info(searchResult.getTotal());
				
		return searchResult;
	}
}
