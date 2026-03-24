package com.green.security.controller;

import com.green.security.dto.MemberDTO;
import com.green.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
  private final MemberService memberService;

  // 회원가입
  @PostMapping("")
  public void join(@RequestBody MemberDTO memberDTO) {
    memberService.join(memberDTO);
  }
}
