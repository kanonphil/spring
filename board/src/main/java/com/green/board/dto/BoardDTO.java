package com.green.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
  private int boardNum;
  private String title;
  private String writer;
  private String content;
  private LocalDateTime regDate;
  private int readCnt;
}
