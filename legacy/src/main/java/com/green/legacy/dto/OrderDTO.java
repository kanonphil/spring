package com.green.legacy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderDTO {
  private String chickenType;
  private int quantity;
  private List<String> options;
  private String request;

  private String name;
  private String phone;
  private String address;

  private int totalPrice;
}
