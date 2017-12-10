/**
 * 
 */
package gz.nozing.library.test.web.controller.book;

import junit.framework.Assert;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.web.controller.book.BookSearchController;
import gz.nozing.library.web.controller.book.dto.BookWebDTO;
import gz.nozing.library.web.controller.book.form.SearchBookFormDTO;
import gz.nozing.library.web.controller.form.PaginationFormResultDTO;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author nozing
 *
 */
public class BookSearchControllerTest {

	@Autowired
	private BookSearchController bookSearchController;
	
	@Test
	public void searchBooksByNameTest() {
		
		/* Cargamos la base de datos con los datos de prueba */
		
		SearchBookFormDTO form = new SearchBookFormDTO();
		form.setTitle("Tit");
		
		ModelAndView mv = null;
		try {
			mv = this.bookSearchController.searchBooks(form);
		} catch (CoreException e) {
			
			Assert.fail(String.format(
					"CoreException thrown: %s", e.getMessage()));
		}
		
		@SuppressWarnings("unchecked")
		PaginationFormResultDTO<BookWebDTO> searchResultForm =
				(PaginationFormResultDTO<BookWebDTO>) mv.getModelMap().get("searchForm");
		
		Assert.assertNotNull(searchResultForm);
	}
}
