package com.green.legacy.dto;

import lombok.Data;

import java.util.List;

@Data
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
