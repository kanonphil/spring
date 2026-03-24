package com.green.security.config;

import com.green.security.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 인증 및 인가에 대한 설정 내용을 작성하는 클래스
 * {@code Configuration}: 객체 생성 + 해당 클래스는 설정 내용이 작성된 파일임을 spring한테 인지
 * {@code EnableWebSecurity}: 해당 파일이 security 설정 파일이며, security 설정을 활성화
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * 인증 및 인가 설정을 작성하는 메서드
   * 해당 메서드는 리턴 타입과 매개변수는 정해져 있음
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
    AuthenticationManager authenticationManager = authConfig.getAuthenticationManager();

    http
            //CORS 설정. 아래 corsConfigurationSource() 메서드에서 정의한 Bean을 등록함.
            .cors(Customizer.withDefaults())
            //csrf disable  세션방식이 아니기 때문에 할 필요 없음
            .csrf(csrf -> csrf.disable())
            //form 로그인 방식 disable
            .formLogin(form -> form.disable())
            //http basic 인증 방식 disable
            .httpBasic(basic -> basic.disable())
            //세션을 STATELESS로 지정
            .sessionManagement(
                    session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            //인증 및 인가에 대한 접근 설정
            .authorizeHttpRequests(auth ->
                    auth.anyRequest().permitAll()  // 인증 및 인가를 받지 않아도 모든 곳에 접근 가능
            );

    http.addFilterAt(new LoginFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  //CORS 설정 Bean
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true); //쿠키 정보를 통신하기 위한 설정
    config.addAllowedOrigin("http://localhost:5173"); //리액트에서의 요청 허용
    config.addAllowedHeader("*"); //모든 헤더 정보 허용
    config.addAllowedMethod("*"); //get, post, delete, put 등의 요청 메서드 허용

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }

  //비밀번호 암호화 기능을 제공하는 객체
  @Bean
  public PasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

}