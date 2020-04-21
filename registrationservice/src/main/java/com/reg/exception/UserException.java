package com.reg.exception;

import lombok.Getter;

@Getter
public class UserException extends Exception {
  
  private static final long serialVersionUID = 1L;
  private final String message;
  
  public UserException(String message) {
    super();
    this.message = message;
  }

}
