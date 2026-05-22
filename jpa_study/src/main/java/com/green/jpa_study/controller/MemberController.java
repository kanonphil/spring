package com.green.jpa_study.controller;

import com.green.jpa_study.dto.MemberRequest;
import com.green.jpa_study.dto.MemberResponse;
import com.green.jpa_study.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/")
  public ResponseEntity<?> findAll() {
    try {
      return ResponseEntity.ok().body(memberService.findAll());
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 회원 등록
  @PostMapping("/")
  public ResponseEntity<?> create(@RequestBody MemberRequest memberRequest) {
    try {
      MemberResponse m = memberService.create(memberRequest);
      return ResponseEntity.ok().body(m);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 회원 상세 조회
  @GetMapping("/{memNum}")
  public ResponseEntity<?> findOne(@PathVariable("memNum") Long memNum) {
    try {
      MemberResponse m = memberService.findOne(memNum);
      return ResponseEntity.ok().body(m);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 회원 정보 수정
  @PutMapping("/{memNum}")
  public ResponseEntity<?> update(@PathVariable("memNum") Long memNum,
                                  @RequestBody MemberRequest memberRequest) {
    try {
      MemberResponse m = memberService.update(memNum, memberRequest);
      return ResponseEntity.ok().body(m);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
