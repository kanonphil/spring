package com.green.legacy.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class UserDTO {
  private String userId;
  private String userName;
  private String userPw;
  private String userRole;
}