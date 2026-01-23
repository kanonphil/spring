package com.green.item.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime regDate;
  private String itemIntro;
}
