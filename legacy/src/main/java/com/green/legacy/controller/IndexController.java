package com.green.legacy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  // URL: localhost:8080
  @RequestMapping("/")
  public String start() {
    // 프로젝트 시작 시 세팅되는 설정, 보완코드 작성

    return "redirect:/students/list";
  }
}
