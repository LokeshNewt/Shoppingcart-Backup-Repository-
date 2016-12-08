package com.newt.shoppingcart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newt.shoppingcart.exception.GlobalException;
import com.newt.shoppingcart.exception.OrderNotFoundException;
import com.newt.shoppingcart.model.ShoppingCart;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("OrderService/")
public class ErrorHandlingController {

	private Log logger = LogFactory.getLog(ErrorHandlingController.class);
	
	@RequestMapping(value = "makeerror/", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Error Handling Controller!!!")
	public void getOrder() {

		throw new NumberFormatException();

	}
	
// Type 1: Throwing Custom Exception
	
	@RequestMapping(value = "makeerror/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Error Handling Controller!!!")
	public void orderNotFound(@PathVariable int id) throws OrderNotFoundException {
		if(id==0)
		{
			throw new OrderNotFoundException("Hai");	
		}		
	}

//Type2: Throwing Global exception
	
	@RequestMapping(value = "makeerror/global/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Error Handling Controller!!!")
	public void globalerror(@PathVariable int id) throws GlobalException {
		throw new GlobalException("Global Error has occured!!!!");
	}
	
	
	
	//Type 3: Handle exception with in a controller.
	//This method will handle all the Number Format Exceptions that arise within this controller
	
	@ExceptionHandler({ NumberFormatException.class})
	public String databaseError(Exception exception) {
		logger.error("Request raised " + exception.getClass().getSimpleName());
		return "NumberFormat Error was occured";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@ExceptionHandler({ OrderNotFoundException.class})
	public String databaseError(Exception exception) {
		logger.error("Request raised " + exception.getClass().getSimpleName());
		return "orderNotFound";
	}*/
	
	
	@POST
	@RequestMapping(value = "responseentity/{value}")
	@ApiOperation(value = "Add Customer")
	@Produces({ "application/json", "application/xml" })
	public ResponseEntity addNamesforlist(@PathVariable("value") String value){
		Map statusMap = new HashMap<>();		
		try{					
		statusMap.put("Status", "SUCCESS");
		statusMap.put("Message","Helloworld");
		if(statusMap!=null)
		{
			throw new GlobalException("OrderId Not Found");	
		}		
		return new ResponseEntity<>(statusMap, HttpStatus.OK);
		}
		catch(GlobalException e){
			System.err.println("Exception"+e);
			statusMap.put("Status", "FAILURE");
			statusMap.put("Message", e.getMessage());
			return new ResponseEntity<>(statusMap,HttpStatus.NOT_FOUND);
		}		
		
	}
}