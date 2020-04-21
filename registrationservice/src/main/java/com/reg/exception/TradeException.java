package com.reg.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TradeException extends Exception {
  
  private static final long serialVersionUID = 1L;
  private String message;
  
  public TradeException(String message) {
    super();
    this.message = message;
  }

}
