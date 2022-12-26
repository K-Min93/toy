package com.curioud.ksmtest.error;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExcptionController {
  
  @ExceptionHandler({NoHandlerFoundException.class})
  public String notFoundExcption(NoHandlerFoundException ex) {
    return "error/error";
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handle(Exception ex) {

    System.out.println(ex.getMessage());
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
