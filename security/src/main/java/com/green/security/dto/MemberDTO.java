package com.green.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDTO {
  private String memEmail;
  private String memPw;
  private String memName;
  private String memRole;
}
