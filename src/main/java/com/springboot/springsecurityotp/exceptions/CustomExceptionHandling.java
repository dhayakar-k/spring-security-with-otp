package com.springboot.springsecurityotp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandling extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {CustomException.class})
  public final ResponseEntity<Object> handleException(CustomException exception, WebRequest request) {

      ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getStatusCode(),
              exception.getTimeStamp(), exception.getMessage(), request.getDescription(false));
      return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
