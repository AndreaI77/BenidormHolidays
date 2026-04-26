package com.andrea.springapirest.exceptions;

public class EmailExistsException extends RuntimeException {
	 public EmailExistsException(String message) {
	        super(message);	    
	}
}
