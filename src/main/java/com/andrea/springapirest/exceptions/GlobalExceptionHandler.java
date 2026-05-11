package com.andrea.springapirest.exceptions;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.andrea.springapirest.utils.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	  @ExceptionHandler(EmailExistsException.class)
	    public ResponseEntity<ApiResponse<Void>> handleEmailExists(EmailExistsException ex) {

	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body(ApiResponse.error("EMAIL_EXISTS", "El email ya está registrado"));
	    }
	  @ExceptionHandler(DNIExistsException.class)
	    public ResponseEntity<ApiResponse<Void>> handleUsuarioExists(DNIExistsException ex) {

	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body(ApiResponse.error("DNI_EXISTS", "El DNI ya está registrado"));
	    }
	  @ExceptionHandler(DNIYEmailExistsException.class)
	    public ResponseEntity<ApiResponse<Void>> handleUsuarioExists(DNIYEmailExistsException ex) {

	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body(ApiResponse.error("DNI_Y_EMAIL_EXISTS", "El DNI y email pertenecen a personas distintas."));
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex) {
	    	 ex.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(ApiResponse.error("INTERNAL_ERROR", "Error interno del servidor"));
	    }
	    
	    @ExceptionHandler(InvalidCredentialException.class)
	    public ResponseEntity<ApiResponse<Void>> handleLogin(InvalidCredentialException ex) {

	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(ApiResponse.error("INVALID_CREDENTIALS","Email o contraseña incorrectos"));
	    }
	   
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error ->
	            errors.put(error.getField(), error.getDefaultMessage())
	        );

	        return ResponseEntity.badRequest().body(errors);
	    }
	    
	    @ExceptionHandler(BindException.class)
	    public ResponseEntity<Map<String, String>> handleBind(BindException ex) {

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error ->
	                errors.put(error.getField(), error.getDefaultMessage())
	        );

	        return ResponseEntity.badRequest().body(errors);
	    }
	    
	   /* @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationErrors(
	            MethodArgumentNotValidException ex) {

	        Map<String, String> errores = new HashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error -> {
	            errores.put(error.getField(), error.getDefaultMessage());
	        });

	        return ResponseEntity.badRequest().body(errores);
	    }*/
	    
	   /* @ExceptionHandler(Exception.class)
	    public ResponseEntity<?> handleAll(Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }*/
}
