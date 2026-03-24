package com.green.security.service;

import com.green.security.dto.CustomUserDetails;
import com.green.security.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final MemberService memberService;

  // AuthenticationManager 객체로 로그인 검증을 시작하면 실행하는 메서드
  // 해당 메서드에서는
  // 1. db에서 로그인하려는 정보를 조회한다.
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("UserDetailsServiceImpl 클래스 안의 loadUserByUsername 메서드 실행");

    // db에서 로그인하려면 회원의 정보를 조회(이메일, 비번, 권한)
    // loadUserByUsername() 메서드의 매개변수 username에는 로그인하려면 회원의 아이디(이메일)정보가 전달
    MemberDTO dto = memberService.selectLoginInfo(username);
    System.out.println(dto);

    // 로그인 시 아이디를 잘못 입력해서 db에서 로그인 정보를 조회하지 못했다면
    if (dto == null) {
      System.out.println("일치하는 아이디 없음");
      // 강제로 오류를 발생시키는 코드 (401)
      throw new UsernameNotFoundException("없는 아이디: " + username);
    }

    // 리턴에는 db에서 조회한 로그인 정보를 리턴한다.
    // 이렇게 리턴된 로그인 정보는 AuthenticationManager한테 전달된다.
    // 다만, 조회한 데이터(dto)를 그대로 리턴하면 안된다.
    // Security에서 제공하는 로그인한 정보를 저장할 수 있도록 만든 UserDetails 인터페이스 자료형에
    // 로그인하려는 회원 정보를 담아서 리턴해야 한다.
    return new CustomUserDetails(dto);
  }
}
