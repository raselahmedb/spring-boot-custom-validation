package com.raselahmedb.customvalidation.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rasel Ahmed
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		Map<String, String> errs = new HashMap<>();

		ex.getAllErrors().forEach(err -> getFieldNameAndValue(err, errs));

		Map<String, Map<String, String>> result = new HashMap<>();
		result.put("errors", errs);
		log.warn(result.toString());
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	private void getFieldNameAndValue(ObjectError err, Map<String, String> errs)
	{
		String key = null;
		String value = null;
		if(err instanceof FieldError)
		{
			key = ((FieldError) err).getField();
			value = errs.get(key);
			if(value != null)
				value = value + "\n" + err.getDefaultMessage();
			else
				value = err.getDefaultMessage();
			errs.put(key, value);
		}
		else
		{
			for(Object obj: err.getArguments())
			{
				if( obj.getClass().getSimpleName().equals("ResolvableAttribute"))
				{
					key = obj.toString();
					value = errs.get(key);
					if(value != null)
						value = value + "\n" + err.getDefaultMessage();
					else
						value = err.getDefaultMessage();
					errs.put(key, value);
				}
			}
		}
		
	}

}