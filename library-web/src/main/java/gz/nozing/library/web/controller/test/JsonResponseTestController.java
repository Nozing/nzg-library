/**
 * 
 */
package gz.nozing.library.web.controller.test;

import gz.nozing.library.web.controller.book.BookAjaxController;
import gz.nozing.library.web.controller.json.dto.BaseJsonResponseDTO;
import gz.nozing.library.web.controller.json.dto.ErrorJsonResponseDTO;
import gz.nozing.library.web.controller.json.dto.SuccessJsonResponseDTO;
import gz.nozing.library.web.controller.json.dto.WarningJsonResponseDTO;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author nozing
 *
 */
@RestController
@RequestMapping("/ajax/test/response")
public class JsonResponseTestController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(BookAjaxController.class);
	
	@RequestMapping(value = "go.do", method = RequestMethod.GET)
	public ModelAndView setup() {
		
		return new ModelAndView("jsonResponseTest");
	}
	
	@RequestMapping(value = "success.do", method = RequestMethod.GET)
	public ResponseEntity<BaseJsonResponseDTO> getSuccess() {
		
		return new ResponseEntity<BaseJsonResponseDTO>(
				new SuccessJsonResponseDTO("Petición procesada con éxito"),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "error.do", method = RequestMethod.GET)
	public ResponseEntity<BaseJsonResponseDTO> getError() {
		
		return new ResponseEntity<BaseJsonResponseDTO>(
				new ErrorJsonResponseDTO("ERROR", "Error en le ejecución del proceso"),
				HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = "warning.do", method = RequestMethod.GET)
	public ResponseEntity<BaseJsonResponseDTO> getWarning() {
		
		return new ResponseEntity<BaseJsonResponseDTO>(
				new WarningJsonResponseDTO("Ha ocurrido algún problema procesando la petición"),
				HttpStatus.OK);
	}
}
