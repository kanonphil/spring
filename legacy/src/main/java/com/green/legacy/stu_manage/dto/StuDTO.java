package com.green.legacy.stu_manage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StuDTO {
  private int stuNum;
  private String stuName;
  private int stuYear;
  private int classNum;
  private ClassDTO classDTO;
}
