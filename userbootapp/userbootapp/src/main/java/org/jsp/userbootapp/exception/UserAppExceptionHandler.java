package org.jsp.userbootapp.exception;

import org.jsp.userbootapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UserAppExceptionHandler  extends ResponseEntityExceptionHandler{
@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException e){
	ResponseStructure<String> structure=new ResponseStructure<String>();
	structure.setCode(HttpStatus.NOT_FOUND.value());
	structure.setMessage(e.getMessage());
	structure.setBody("user not found");
	return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
}
}
