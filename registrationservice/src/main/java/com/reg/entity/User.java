package com.reg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER")
@NoArgsConstructor
public class User {
  
  @Id
  @GeneratedValue
  private long userId;
  private String name;
  private String password;
  private long balance;
  
}
