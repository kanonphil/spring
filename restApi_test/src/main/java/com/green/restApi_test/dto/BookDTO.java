package com.green.restApi_test.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
  private int bookNum;
  private String bookTitle;
  private String author;
  private String bookIntro;
  private int bookPrice;
}
