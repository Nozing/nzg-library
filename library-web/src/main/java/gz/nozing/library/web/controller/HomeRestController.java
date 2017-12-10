package gz.nozing.library.web.controller;

import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.web.controller.json.dto.BaseJsonResponseDTO;
import gz.nozing.library.web.controller.json.dto.SuccessJsonResponseDTO;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nozing
 *
 */
@RestController(value="restHomeController")
@RequestMapping("/rest/home")
public class HomeRestController {

    private static Logger log = Logger.getLogger(HomeRestController.class);
        
    /**
     * @throws CoreException 
     */
    @RequestMapping(value = "/version.do", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BaseJsonResponseDTO> version() throws CoreException {
    	
    	log.info("Entering 'version' method");
    	
    	return new ResponseEntity<BaseJsonResponseDTO>(
        		new SuccessJsonResponseDTO("It works!"), HttpStatus.OK);
    }
}
