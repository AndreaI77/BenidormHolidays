package com.andrea.springapirest.exceptions;

public class DNIExistsException extends RuntimeException{
	public DNIExistsException(String message) {
        super(message);
    }
}
