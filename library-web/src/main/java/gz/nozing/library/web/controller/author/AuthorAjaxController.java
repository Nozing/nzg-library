package gz.nozing.library.web.controller.author;

import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.web.controller.json.dto.BaseJsonResponseDTO;
import gz.nozing.library.web.controller.json.dto.SuccessJsonResponseDTO;
import gz.nozing.library.web.service.author.AuthorService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax/author")
public class AuthorAjaxController {

	private static Logger log = Logger.getLogger(AuthorAjaxController.class);
	
	@Autowired
	private AuthorService authorService = null;
	
	/**
	 * @param authorId
	 * @return
	 * @throws CoreException
	 */
	@RequestMapping(value = "/deleteAuthor.htm", method = RequestMethod.GET)
	public ResponseEntity<BaseJsonResponseDTO> deleteAuthor(
			@RequestParam(value = "id", required = true) String authorId)
		throws CoreException {
		
		log.info(String.format("Autor id to delete %s", authorId));
		
		this.authorService.deleteAuthor(authorId);
		
		return new ResponseEntity<BaseJsonResponseDTO>(
				new SuccessJsonResponseDTO("_Autor borrado correctamente_"),
				HttpStatus.OK);
	}
}
