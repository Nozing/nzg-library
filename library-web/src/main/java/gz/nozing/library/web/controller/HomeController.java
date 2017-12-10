package gz.nozing.library.web.controller;

import gz.nozing.library.core.exception.CoreException;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author nozing
 *
 */
@Controller(value="homeController")
public class HomeController {

    private static Logger log = Logger.getLogger(HomeController.class);
        
    /**
     * @throws CoreException 
     */
    @RequestMapping(value = "about.htm", method = RequestMethod.GET)
    public ModelAndView about(Locale locale) throws CoreException {
    	 
        ModelAndView model = new ModelAndView("about");
       
        model.addObject("version", "1.0");
        
        return model;
    }
    
    /**
     * @throws CoreException 
     */
    @RequestMapping(value = "index.htm", method = RequestMethod.GET)
    public ModelAndView index(Locale locale) throws CoreException {
    	 
        ModelAndView model = new ModelAndView("index");
        
        return model;
    }
}
