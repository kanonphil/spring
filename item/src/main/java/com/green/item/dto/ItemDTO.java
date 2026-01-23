package com.green.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
  private int itemNum;
  private String itemName;
  private int itemPrice;
  private LocalDateTime regDate;
  private String itemIntro;
}
