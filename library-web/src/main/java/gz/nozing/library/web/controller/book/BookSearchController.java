/**
 * 
 */
package gz.nozing.library.web.controller.book;

import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.book.AuthorDTO;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.web.controller.book.dto.AuthorBookWebDTO;
import gz.nozing.library.web.controller.book.dto.BookWebDTO;
import gz.nozing.library.web.controller.book.form.SearchBookFormDTO;
import gz.nozing.library.web.controller.form.PaginationFormResultDTO;
import gz.nozing.library.web.service.book.BookService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author nozing
 * 
 */
@Controller(value = "bookSearchController")
public class BookSearchController {

	public static final String SEARCH_HOME_MAPPING = "/book/searchForm.htm";

	private static Logger log = Logger.getLogger(BookSearchController.class);

	@Autowired
	private BookService bookService;

	/**
	 * @return
	 */
	@RequestMapping(value = SEARCH_HOME_MAPPING, method = RequestMethod.GET)
	public ModelAndView startPage() {

		log.trace("Entering 'startPage'");
		ModelAndView searchFormPage = new ModelAndView("book.searchForm");

		searchFormPage.addObject("searchForm", new SearchBookFormDTO());

		return searchFormPage;
	}

	/**
	 * @param form
	 * @return
	 * @throws CoreException
	 */
	@RequestMapping(value = SEARCH_HOME_MAPPING, method = RequestMethod.POST)
	public ModelAndView searchBooks(@ModelAttribute SearchBookFormDTO form)
			throws CoreException {

		ModelAndView searchResult = new ModelAndView("book.searchForm");

		log.debug(String.format("Search form: %s", form));
		
		PaginationFormResultDTO<BookWebDTO> searchResultForm = 
				bookService.findBooksByFormCriteria(form);
		
		searchResult.addObject("searchResult", searchResultForm);
		
		form.setTotal(searchResultForm.getTotal());
		form.setInitial(searchResultForm.getInitial());
		form.setPageSize(searchResultForm.getPageSize());
		
		searchResult.addObject("searchForm", form);
		
		log.debug(String.format("Search result: %s", searchResultForm));
		
		return searchResult;
	}
}
