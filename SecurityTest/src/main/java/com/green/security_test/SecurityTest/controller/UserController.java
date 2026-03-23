package com.green.security_test.SecurityTest.controller;

import com.green.security_test.SecurityTest.dto.MemberDTO;
import com.green.security_test.SecurityTest.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class UserController {
  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;

  //회원가입
  @PostMapping("/join")
  public ResponseEntity<?> doJoin(@RequestBody MemberDTO memberDTO){
    //비밀번호 암호화
    memberDTO.setMemPw(passwordEncoder.encode(memberDTO.getMemPw()));
    memberService.join(memberDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/user-page")
  public ResponseEntity<?> userPage(){
    System.out.println("userPage 메서드 접근 및 실행~");
    return ResponseEntity.ok("success");
  }

}
