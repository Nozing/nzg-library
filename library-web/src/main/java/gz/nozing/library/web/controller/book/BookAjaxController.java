package gz.nozing.library.web.controller.book;

import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.core.exception.EntityNotFoundCoreException;
import gz.nozing.library.web.controller.book.form.UpdateBookFormDTO;
import gz.nozing.library.web.controller.json.dto.BaseJsonResponseDTO;
import gz.nozing.library.web.controller.json.dto.ErrorJsonResponseDTO;
import gz.nozing.library.web.controller.json.dto.SuccessJsonResponseDTO;
import gz.nozing.library.web.service.book.BookService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax/book")
public class BookAjaxController {

	private static Logger log = Logger.getLogger(BookAjaxController.class);
	
	@Autowired
	private BookService bookServ;
	
	/**
	 * @param bookId
	 * @return
	 * @throws CoreException
	 */
	@RequestMapping(value = "/remove.do", method = RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<BaseJsonResponseDTO> deleteBook(
			@RequestParam(value = "id", required = true) String bookId)
		throws CoreException {
		
		try {
			
			this.bookServ.deleteBook(bookId);
		} catch (EntityNotFoundCoreException enfce) {
			
			return new ResponseEntity<BaseJsonResponseDTO>(new ErrorJsonResponseDTO(
					"NOT_FOUND", enfce.getMessage()), HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<BaseJsonResponseDTO>(
				new SuccessJsonResponseDTO(String.format(
						"Book #%s has been deleted", bookId)), HttpStatus.OK);
	}
	
	/**
	 * @param updateForm
	 * @return
	 * @throws CoreException
	 */
	@RequestMapping(value = "/update.do", method = RequestMethod.POST, produces="application/json")	
	public ResponseEntity<BaseJsonResponseDTO> updateBook(
			@ModelAttribute UpdateBookFormDTO updateForm)
		throws CoreException {
		
		try {
		
			this.bookServ.updateBook(updateForm);
		} catch (CoreException ce) {
			
			log.error(String.format("Error updating book %s using DTO %s: %s",
					updateForm.getId(), updateForm.toString(),
					ce.getMessage()));
			
			ce.printStackTrace();
			
			return new ResponseEntity<BaseJsonResponseDTO>(
					new ErrorJsonResponseDTO("UNEXPECED_ERROR", ce.getMessage()),
					HttpStatus.NOT_MODIFIED);
		}
		
		return new ResponseEntity<BaseJsonResponseDTO>(
				new SuccessJsonResponseDTO("Operacion realizada con éxito"), HttpStatus.OK);
	}
}
