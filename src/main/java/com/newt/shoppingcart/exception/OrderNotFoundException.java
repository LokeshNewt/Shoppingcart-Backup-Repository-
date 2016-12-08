package com.newt.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An example of an application-specific exception. Defined here for convenience
 * as we don't have a real domain model or its associated business logic.
 */
public class OrderNotFoundException extends Exception  {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = -8790211652911971729L;

	public OrderNotFoundException(String orderId) {
		super(orderId + " not found");
	}
	
	public OrderNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}


}
