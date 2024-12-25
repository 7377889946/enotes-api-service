package com.crazycoder.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<?>
	 * handleGlobalException(Exception e){
	 * log.error("Global Exception handler :: HandleGlobalException :- ",e.
	 * getMessage()); return new
	 * ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR); }
	 */
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(NullPointerException e){
		log.error("Global Exception handler :: HandleNullPointerException :- ",e.getMessage());
		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResouceNotFoundException(ResourceNotFoundException e){
		log.error("Global Exception handler :: HandleResourceNotFoundException :- ",e.getMessage());
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotFoundException(MethodArgumentNotValidException e){
		
		java.util.List<ObjectError> bindingError=e.getBindingResult().getAllErrors();
		
		Map<String, Object> errorContainer=new LinkedHashMap<>();
		
		bindingError.stream().forEach(error -> {
			String field = ((FieldError) error).getField();
			String msg = error.getDefaultMessage();
		    errorContainer.put(field, msg);
		});
		
		return new ResponseEntity<>(errorContainer,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(dtoValidationException.class)
	public ResponseEntity<?> handleCategoryValidationException(dtoValidationException e){
		return new ResponseEntity<>(e.getErrors(),HttpStatus.BAD_REQUEST);
	}
	
}
