package com.green.rest_study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// react에서 들어온 요청에 대한 응답하는 클래스 -> API
@RestController // 객체 생성 + REST 문법을 적용한 컨트롤러 역할 여부
public class TestController {

  // 회원 목록 조회 api
  @GetMapping("/members")
  public String getMemberList() {
    System.out.println("회원 목록을 조회합니다.");
    return "김자바, 박자바, 최자바";
  }

  // 3번 회원의 정보를 조회하는 api
  @GetMapping("/members/3")
  public String getMemberDetail() {
    System.out.println("3번 회원을 조회합니다.");
    return "최자바";
  }

  // 회원 등록 api
  @PostMapping("/members")
  public int regMember() {
    System.out.println("회원을 추가합니다.");
    return 1; // 1: 등록 성공, 0: 등록 실패
  }
}
