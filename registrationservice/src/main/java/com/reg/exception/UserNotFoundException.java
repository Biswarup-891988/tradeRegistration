package com.reg.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends Exception {
  
  private static final long serialVersionUID = 1L;
  private final String message;
  
  public UserNotFoundException(String message) {
    super();
    this.message = message;
  }

}
