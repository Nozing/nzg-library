/**
 * 
 */
package gz.nozing.library.web.controller.book;

import gz.nozing.library.common.utils.CommonUtils;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.web.controller.book.dto.BookWebDTO;
import gz.nozing.library.web.controller.book.form.InsertBookAuthorFormDTO;
import gz.nozing.library.web.controller.book.form.InsertBookFormDTO;
import gz.nozing.library.web.service.book.BookService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author nozing
 * 
 */
@Controller(value="bookCreateController")
public class BookCreateController {

	public static final String HOME_MAPPING = "/book/insertForm.htm";
	public static final String CREATE_MAPPING = "/book/insertBook.htm";
	
	private static Logger log = Logger.getLogger(BookCreateController.class);

	@Autowired
	private BookService bookServ;

	@SuppressWarnings("serial")
	@RequestMapping(value = HOME_MAPPING, method = RequestMethod.GET)
	public ModelAndView setup() {
		
		log.trace("Entering 'setup'");
		ModelAndView mv = new ModelAndView("book.insertForm");
		
		final InsertBookAuthorFormDTO author = new InsertBookAuthorFormDTO();
		author.setName("");
		
		InsertBookFormDTO form = new InsertBookFormDTO();
		form.setAuthors(new LinkedList <InsertBookAuthorFormDTO>() {{ add(author); }});
		mv.addObject("insertForm", form);
		
		log.trace("Leaving 'setup'");
		return mv;		
	}
	
	@RequestMapping(value = CREATE_MAPPING, method = RequestMethod.POST)
	public ModelAndView insertBook(@ModelAttribute InsertBookFormDTO form)
			throws CoreException {

		log.trace("Entering 'insertBook'");
		log.trace(String.format("Form received: %s", form));
		ModelAndView mv = new ModelAndView("book.showDetails");

		/* Form validation */
		List<InsertBookAuthorFormDTO> authors = new ArrayList<InsertBookAuthorFormDTO>();
		for (InsertBookAuthorFormDTO author : form.getAuthors()) {
			
			if (!CommonUtils.isEmpty(author.getName())
					&& !CommonUtils.isEmpty(author.getSurname())
					&& !authors.contains(author)) {
				
				authors.add(author);
			}
		}
		
		form.setAuthors(authors);
		
		/* Call service to store the book */
		BookWebDTO newBook = this.bookServ.insertBook(form);
		
		mv.addObject("bookDetailsForm", newBook);

		log.trace("Leaving 'insertBook'");
		return mv;
	}
}
