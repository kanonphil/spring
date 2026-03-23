package com.green.security_test.SecurityTest.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.security_test.SecurityTest.dto.MemberDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

/**
 * UsernamePasswordAuthenticationFilter 클래스는 Spring Security에서 로그인 기능을 담당하는 클래스임
 * UsernamePasswordAuthenticationFilter 클래스를 상속받아 클래스를 구현한다는 것은
 * Spring Security에서 기본 설정으로 진행되는 로그인 진행 방식을 커스터마이징하기 위해서임.
 * 즉, 우리가 만드는 LoginFilter 클래스는 로그인 절차를 우리 입맛대로 변경하기 위해 생성하는 클래스임.
 * 우리는 해당 클래스를 상속받아, 기본적인 로그인 방식이 아닌, jwt 토큰을 사용한 로그인 방식으로 변경하여 사용함.
 * 해당 클래스 위에 객체 생성을 위한 어노테이션을 사용하지 않는 이유는 해당 클래스의 객체 사용을 위해서는
 * 문법적으로 SecurityFilterChain에 직접 객체를 생성하고 추가하는 구조를 지니기 때문임.
 *
 * --- UsernamePasswordAuthenticationFilter의 기본동작 ---
 * UsernamePasswordAuthenticationFilter 클래스는 기본적으로 '/login' 경로의 post 요청을 처리함
 * 이때, 사용자의 아이디와 비밀번호는 각각 'username', 'password'라는 key 값으로 전달받아 옴.
 * 그리고 전달받은 아이디 및 비밀번호 데이터를 AuthenticationManager에게 전달하며 인증 절차를 위임함.
 * 결국 인증처리는 AuthenticationManager가 진행 함.
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  //생성자를 통해 AuthenticationManager 객체를 의존성 주입 받는다.
  public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;

    //로그인 요청 url 설정
    setFilterProcessesUrl("/member/login");
    //전달되는 아이디, 비번 key값 변경
    setUsernameParameter("memEmail");
    setPasswordParameter("memPw");
  }

  //로그인 절차가 진행되면 LoginFilter 클래스의 attemptAuthentication() 메서드가 실행된다.
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    log.info("attemptAuthentication 메서드 실행");

    MemberDTO vo = new MemberDTO();
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      ServletInputStream inputStream = request.getInputStream();
      String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
      vo = objectMapper.readValue(messageBody, MemberDTO.class);
    }catch (IOException e){
      throw new RuntimeException(e);
    }
    log.info("입력받은 아이디 : " + vo.getMemEmail());
    log.info("입력받은 비밀번호 : " + vo.getMemPw());

    //우리가 입력한 아이디와 비밀번호를 데이터베이스에 저장한 정보와 일치하는지 검증하는 로직은
    //AuthenticationManager가 담당하기 때문에 전달받은 아이디와 비밀번호를 AuthenticationManager에 전달해줘야 한다.
    //이때 아이디와 비밀번호를 그냥 전달하는 것이 아니라 UsernamePasswordAuthenticationToken 객체에 실어 보낸다.
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(vo.getMemEmail(), vo.getMemPw(), null);

    //아이디와 비번을 담고 있는 authToken객체를 authenticationManager에 전달, authenticationManager는 로그인을 검증하는 기능을 함
    //로그인을 검증하는 방법 -> UserDetailsService의 loadUserByUsername 메서드를 호출하여 검증
    //loadUserByUsername() 메서드의 실행 결과로 로그인 유저의 정보를 authentication 객체에 담아 옴
    Authentication authentication = authenticationManager.authenticate(authToken);
    log.info("DB에서 로그인 가능 여부 확인 완료(UserDetailsService의 loadUserByUsername 메서드 정상 실행 됨). 만약 검증에 실패했다면 본 출력문 실행 안 됨");
    log.info("로그인 중인 유저 : " + authentication.getName());

    //로그인 유저의 정보가 담긴 authentication객체를 리턴하면 authentication객체가 session에 저장됨
    //세션에 저장하는 이유는 security의 권한 처리를 위해서는 세션에 로그인 정보가 있어야 되기 때문.
    return authentication;
  }

  //로그인 검증이 성공했을 때 실행되는 메서드
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    log.info("로그인 검증 성공! successfulAuthentication 메서드 호출!");

    //토큰 생성을 위한 아이디 정보 추출
    String username = authResult.getName();

    //토큰 생성을 위한 권한 정보 추출
    Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
    Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
    GrantedAuthority auth = iterator.next();
    String role = auth.getAuthority();

    //토큰 생성
    String accessToken = jwtUtil.createJwt(username, role, (1000 * 60 * 30)); //30분

    //생성한 토큰을 응답 헤더에 담아 클라이언트에 전달
    response.setHeader("Access-Control-Expose-Headers", "Authorization");
    response.setHeader("Authorization", "Bearer " + accessToken);
    response.setStatus(HttpStatus.OK.value()); //클라이언트에 200 응답
  }

  //로그인 검증이 실패했을 때 실행되는 메서드. 아이디는 맞지만 비번이 틀렸을 경우 실행
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    log.info("로그인 검증 실패! unsuccessfulAuthentication 메서드 호출!");

    //로그인 실패시 401 응답 코드 반환
    response.setStatus(401);
  }
}


