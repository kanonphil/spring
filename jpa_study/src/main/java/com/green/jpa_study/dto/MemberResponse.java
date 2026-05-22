package com.green.jpa_study.dto;

import com.green.jpa_study.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 조회한 데이터를 자바로 가져오기 위한 DTO
@Getter
@Setter
@Builder
public class MemberResponse {
  private Long memNum;
  private String memEmail;
  private String memName;
  private Integer memAge;
  private LocalDateTime createAt;

  // 매개변수로 Member 객체가 전달되면 MemberResponse 객체로 변환 후 리턴
  public static MemberResponse from(Member member) {
    return MemberResponse.builder()
            .memNum(member.getMemNum())
            .memEmail(member.getMemEmail())
            .memName(member.getMemName())
            .memAge(member.getMemAge())
            .createAt(member.getCreateAt())
            .build();
  }
}
