package com.reg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="USER_PORTFOLIO")
@Getter
@Setter
@NoArgsConstructor
public class UserPortfolio {
  
  @Id
  @GeneratedValue
  private long id;
  private long userId;
  private String ticker;
  private long units;
  private long amount;
  
  

}
