package com.reg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.reg.exception.PasswordException;
import com.reg.exception.UserException;

@RestControllerAdvice
public class ResgistrationControllerAdvice {

  @ExceptionHandler
  public ResponseEntity<String> handleUserNotFoundException(UserException ex) {
    return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<String> handleIncorrectPasswordException(PasswordException ex) {
    return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
  }

}
