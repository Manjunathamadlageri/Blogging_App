package com.manjunath.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.manjunath.blog.payload.ApiResponse;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String massege = ex.getMessage();
		ApiResponse apiResponse= new ApiResponse(massege,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND); 
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String,String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldname =((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldname, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex)
	{
		String massege = ex.getMessage();
		ApiResponse apiResponse= new ApiResponse(massege,true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST); 
	}
}
