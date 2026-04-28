package com.green.legacy.controller;

import com.green.legacy.dto.BoardDTO;
import com.green.legacy.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 객체 생성, 해당 클래스는 컨트롤러 역할임을 스프링한테 인지(비동기 미지원)
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
  private final BoardService boardService;

  // 게시글 목록('/boards/getList')
  // 조회, 등록, 삭제, 수정이든 전부 @RequestMapping 어노테이션 사용
  // 컨트롤러에서 요청을 처리하는 메서드의 리턴 타입은 무조건 문자열(아래처럼 html 파일명을 리턴하기 때문)
  // 레거시 방식에서는 리턴되는 정보는 보여질 html 파일명을 의미함
  // 매개변수에 선언한 Model 인터페이스 객체는 데이터를 적재하는 역할
  @RequestMapping("/getList")
  public String getList(Model model) {
    // 필요한 데이터 조회
    String name = "hong";
    int age = 20;

    // 조회한 데이터를 실음
    model.addAttribute("data", name);
    model.addAttribute("ageData", age);
    model.addAttribute("boardList", boardService.selectBoardList());

    // html 파일은 무조건 resources/templates 폴더 안에 위치
    return "board_list";
  }

  // 글쓰기 페이지로 이동
  @RequestMapping("/go-write")
  public String goWrite() {
    return "reg_board";
  }

  // 글 등록 기능 실행
  // @ModelAttribute 클래스명 객체명
  // -> input 태그의 name 속성과 동일한 맴버변수를 가진 dto 클래스로 데이터 받음
  @RequestMapping("/write")
  public String write(@ModelAttribute BoardDTO boardDTO) {
    boardService.regBoard(boardDTO);
    return "redirect:/boards/getList";
  }

  @RequestMapping("/write2")
  public String write2(@RequestParam(name = "name") String name,
                       @RequestParam(name = "age") String age) {
    System.out.println("name: " + name);
    System.out.println("age: " + age);
    return "";
  }
}
