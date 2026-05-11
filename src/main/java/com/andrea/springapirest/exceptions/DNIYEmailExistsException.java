package com.andrea.springapirest.exceptions;

public class DNIYEmailExistsException extends RuntimeException {
	public DNIYEmailExistsException(String message) {
        super(message);
    }
}
