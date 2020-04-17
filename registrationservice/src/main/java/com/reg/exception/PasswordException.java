package com.reg.exception;

import lombok.Getter;

@Getter
public class PasswordException extends Exception {
  
  private static final long serialVersionUID = 1L;
  private final String message;
  
  public PasswordException(String message) {
    super();
    this.message = message;
  }

}
