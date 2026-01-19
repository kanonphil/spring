package com.green.rest_study;

import lombok.*;

// 게시글 하나의 정보를 저장할 수 있는 자료형
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
  private Integer boardNum;
  private String title;
  private String writer;
  private Integer readCnt;
}
