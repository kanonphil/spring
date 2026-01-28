package com.green.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDTO {
  private int replyNum;
  private String content;
  private String writer;
  private LocalDateTime regDate;
  private int boardNum;
}
