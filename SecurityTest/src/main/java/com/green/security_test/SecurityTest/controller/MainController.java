package com.green.security_test.SecurityTest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

  @GetMapping("/test1")
  public String test1(){
    return "test1";
  }

  @GetMapping("/test2")
  public String test2(){
    System.out.println("test2 method run~");
    return "test2";
  }

  @GetMapping("/test3")
  public String test3(){
    System.out.println("test3 method run~");
    return "test3";
  }

  @GetMapping("/test4")
  public String test4(){
    System.out.println("test4 method run~");
    return "test4";
  }

  @GetMapping("/test5")
  public String test5(){
    System.out.println("test5 method run~");
    return "test5";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/test6")
  public String test6(){
    System.out.println("test6 method run~");
    return "test6";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/test7")
  public String test7(){
    System.out.println("test7 method run~");
    return "test7";
  }

  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  @GetMapping("/test8")
  public String test8(){
    System.out.println("test8 method run~");
    return "test4";
  }

}


