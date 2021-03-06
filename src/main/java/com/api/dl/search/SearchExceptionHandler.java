package com.api.dl.search;
import java.util.HashMap;
import java.util.Map;

import com.api.dl.publication.exceptions.InvalidPublicationException;
import com.api.dl.search.exceptions.InvalidSearchException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class SearchExceptionHandler {
	private Map<String, Object> model = new HashMap<String, Object>();
		
	@ExceptionHandler(InvalidSearchException.class)
	public ResponseEntity<Map<String, Object>> handleCustomException (InvalidSearchException e) {
		return mountBadRequestException(e.getMessage());
	}

	@ExceptionHandler(InvalidPublicationException.class)
	public ResponseEntity<Map<String, Object>> handleCustomException (InvalidPublicationException e) {
		return mountBadRequestException(e.getMessage());
	}
	
	private ResponseEntity<Map<String, Object>> mountBadRequestException (String errorMessage){
		model.put("error", errorMessage);
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.BAD_REQUEST);
	}
}