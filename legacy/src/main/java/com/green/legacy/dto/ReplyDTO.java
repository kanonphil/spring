package com.green.legacy.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReplyDTO {
  private int replyNum;
  private String replyContent;
  private String replyWriter;
  private LocalDateTime regDate;

  private int boardNum;
}
