package com.green.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.security.dto.MemberDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;

  // 생성자 의존성 주입 방식으로 맴버변수 AuthenticationManager를 생성
  public LoginFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;

    // 로그인 요청 url 변경
    setFilterProcessesUrl("/member/login");

    // 전달되는 아이디 및 비번 key 값 변경
    setUsernameParameter("memEmail");
    setPasswordParameter("memPw");
  }

  // Spring Security의 기본 로그인 절차
  // 1. 로그인 요청이 들어오면 UsernamePasswordAuthenticationFilter 클래스의
  //    attemptAuthentication() 메서드가 실행되면서 로그인을 진행
  //
  // 우리가 하고 싶은 방식
  // 1. 로그인 요청이 들어오면 LoginFilter 클래스의 attemptAuthentication() 메서드를 실행
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    System.out.println("LoginFilter 클래스의 attemptAuthentication() 메서드 실행");

    // 전달된 아이디 및 비번을 저장하기 위한 객체
    MemberDTO vo = new MemberDTO();

    // 로그인 요청 시 전달되는 아이디 및 비번을 받는 코드
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      ServletInputStream inputStream = request.getInputStream();
      String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
      vo = objectMapper.readValue(messageBody, MemberDTO.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // 전달받은 로그인 정보 확인
    System.out.println("전달받은 아이디: " + vo.getMemEmail());
    System.out.println("전달받은 비밀번호: " + vo.getMemPw());

    // 전달받은 로그인 정보를 AuthenticationManager 객체에게 전달
    // 그러면 AuthenticationManager 객체로 로그인 검증을 시작

    // 1). 전달받은 로그인 정보를 보안기능이 추가된 객체에 등록
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(vo.getMemEmail(), vo.getMemPw(), null);

    // 2). authenticationManager한테 로그인 정보가 담긴 상자(authToken)를 전달
    //     authenticationManager가 로그인 정보를 전달받으면 바로 로그인 검증 로직을 실행
    //     로그인 검증 로직을 실행하면 UserDetailsService 인터페이스 안에 정의된
    //     loadUserByUsername 메서드를 우선 호출한다.

    //     authentication 객체에는 로그인 검증에 대한 결과 데이터가 들어있음
    Authentication authentication = authenticationManager.authenticate(authToken);

    // 검증 성공한 유저의 아이디(이메일)을 출력
    System.out.println("로그인 유저: " + authentication.getName());


    // 로그인 검증 결과를 리턴
    // 결과에 따라 successfulAuthentication 혹은 unsuccessfulAuthentication 중 하나가 실행 됨
    return authentication;
  }

  // 로그인 검증 성공 시 실행하는 메서드
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    System.out.println("로그인 검증 성공 - LoginFilter 클래스의 successfulAuthentication 메서드 실행");
  }

  // 로그인 검증 실패 시 실행하는 메서드
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    System.out.println("로그인 검증 실패 - LoginFilter 클래스의 unsuccessfulAuthentication 메서드 실행");
  }
}
