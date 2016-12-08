package com.newt.shoppingcart.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/*
 * This controller is used to handle global exception
 * 
 * */

@ControllerAdvice
public class ExceptionController {

	
	  
	
	
	
	private Log logger = LogFactory.getLog(ExceptionController.class);
	
	
	
	  /**
	   * 
	   * The below method for Order Not Found Exception 
	   * 
	   * */
	@ExceptionHandler(value = OrderNotFoundException.class)
    public String handleOrderError(HttpServletRequest req, Exception exception) {
        logger.error("Request: " + req.getRequestURL() + " raised " + exception);
        return "orderNotFound";		//view name
    }	    
	
	
	
	
	  /**
	   * 
	   * The below method for default error 
	   * 
	   * */	  
	    @ExceptionHandler(value = Exception.class)
	    public String handleError(HttpServletRequest req, Exception exception) {
	        logger.error("Request: " + req.getRequestURL() + " raised " + exception);
	        return "error";		//view name
	    }	    

	    /*
	     * The Below method used to handle global exception and display the message using in
	     * JSP using the model and view we are populating error message in ui.
	     * 
	     * */
	    @ExceptionHandler(GlobalException.class)
		public ModelAndView globalerror(HttpServletRequest req, Exception exception) throws Exception {

			// Rethrow annotated exceptions or they will be processed here instead.
			if (AnnotationUtils.findAnnotation(exception.getClass(),
					ResponseStatus.class) != null)
				throw exception;

			logger.error("Request: " + req.getRequestURI() + " raised " + exception);

			ModelAndView mav = new ModelAndView();
			mav.addObject("exception", exception);
			mav.addObject("url", req.getRequestURL());
			mav.addObject("timestamp", new Date().toString());
			mav.addObject("status", 500);

			mav.setViewName("Globalexception");		//viewname
			return mav;
		}
	}