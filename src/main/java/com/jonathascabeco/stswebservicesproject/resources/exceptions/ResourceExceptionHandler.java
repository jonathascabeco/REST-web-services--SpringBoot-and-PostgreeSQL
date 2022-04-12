package com.jonathascabeco.stswebservicesproject.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jonathascabeco.stswebservicesproject.services.exceptions.ResourceNotFoundException;


@ControllerAdvice
//intercepta as exceções para a class fazer o tratamento dimensionado.
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)// interceptando qualquer tipo de exceção do parametro;
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);		
	}
}
