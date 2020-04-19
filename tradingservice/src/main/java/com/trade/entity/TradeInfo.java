package com.trade.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Trade")
public class TradeInfo {

  @Id
  private long id;
  private String ticker;
  private long unitPrice;
  public TradeInfo(long id, String ticker, long unitPrice) {
    super();
    this.id = id;
    this.ticker = ticker;
    this.unitPrice = unitPrice;
  }


}
