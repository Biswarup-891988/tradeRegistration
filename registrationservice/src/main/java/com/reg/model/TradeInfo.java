package com.reg.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TradeInfo {

  private long id;
  private String ticker;
  private long unitPrice;

}
