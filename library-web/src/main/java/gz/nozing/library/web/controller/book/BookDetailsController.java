package gz.nozing.library.web.controller.book;

import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.book.BookDO;
import gz.nozing.library.web.controller.book.dto.BookWebDTO;
import gz.nozing.library.web.service.book.BookService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller(value="bookDetailsController")
public class BookDetailsController {

	public static final String SHOW_DETAILS_MAPPING = "/book/viewBookDetail.htm";
	public static final String DELETE_BOOK_MAPPING = "/book/deleteBook.htm";
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(BookDetailsController.class);
	
	@Autowired
	private BookService bookService;
	
	/**
	 * @param id
	 * @return
	 * @throws CoreException
	 */
	@RequestMapping(value = SHOW_DETAILS_MAPPING, method = RequestMethod.GET)
	public ModelAndView showDetails(@RequestParam String id)
			throws CoreException {

		ModelAndView bookDetailView = new ModelAndView("book.showDetails");

		BookDO book = this.bookService.findById(id);

		BookWebDTO bookForm = null;
		if (book != null) {

			bookForm = BookWebDTO.buildFrom(book);
		} else {

			bookForm = new BookWebDTO();
		}

		return bookDetailView.addObject("bookDetailsForm", bookForm);
	}
}
