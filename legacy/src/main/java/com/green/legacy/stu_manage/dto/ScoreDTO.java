package com.green.legacy.stu_manage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreDTO {
  private int scoreNum;
  private int korScore;
  private int engScore;
  private int mathScore;
  private int stuNum;

  private StuDTO stuDTO;
}
