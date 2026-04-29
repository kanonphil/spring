package com.green.security.controller;

import com.green.security.dto.MemberDTO;
import com.green.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
  private final MemberService memberService;

  // 리액트 네이티브 테스트
  @GetMapping("/t1")
  public String t1() {
    return "aaa";
  }

  // 회원가입
  @PostMapping("")
  public void join(@RequestBody MemberDTO memberDTO) {
    memberService.join(memberDTO);
  }

  // 해당 메서드는 인증 받은 사람이면 실행 가능
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/test1")
  public void test1() {
    System.out.println("test1 메서드 실행");
  }

  // ADMIN 권한이 있는 유저만 실행 가능
  // 주의사항: 데이터베이스에 권한을 등록할 때는 접두사로 'ROLE_' 추가했지만
  //         PreAuthorize 어노테이션 사용 시에는 'ROLE_'는 생략
  // 로그인 했으면 가능
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/test2")
  public void test2() {
    System.out.println("test2 메서드 실행");
  }

  // PreAuthorize 어노테이션을 사용하지 않으면 인증받지 않은 유저도 실행 가능
  @GetMapping("/test3")
  public void test3() {
    System.out.println("test3 메서드 실행");
  }
}
