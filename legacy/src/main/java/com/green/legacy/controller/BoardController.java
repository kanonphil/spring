package com.green.legacy.controller;

import com.green.legacy.dto.BoardDTO;
import com.green.legacy.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    // 1) 게시글 목록을 조회 -> boardList라는 이름으로 전달
    // 2) '/boards/getList' 요청 실행

    // return에는 기본적으로 html 파일명을 작성
    // 추가적으로 html 파일명이 아니라, 컨트롤러의 다른 메서드를 호출
    // -> "redirect:요청url"
    return "reg_result";  // 게시글 등록 성공 여부에 따라 alert를 띄울 목적의 html
  }

  // 글 상세보기
  @RequestMapping("/detail")
  public String detail(@RequestParam int boardNum, Model model) {
    BoardDTO board = boardService.selectBoardDetail(boardNum);

    model.addAttribute("board", board);

    return "board_detail";
  }

  // 글 삭제
  @RequestMapping("/delete")
  public String delete(@RequestParam("boardNum") int boardNum) {
    boardService.deleteBoard(boardNum);

//    return "redirect:/boards/getList";
    return "delete_result";
  }

  // 글 수정 페이지 이동
  @RequestMapping("/edit")
  public String edit(@RequestParam("boardNum") int boardNum, Model model) {
    BoardDTO board = boardService.selectBoardDetail(boardNum);
    model.addAttribute("board", board);

    return "board_edit";
  }

  // 글 수정
  @RequestMapping("/update")
  public String update(@ModelAttribute BoardDTO boardDTO) {
    boardService.updateBoard(boardDTO);

    return "redirect:/boards/detail?boardNum=" + boardDTO.getBoardNum();
  }
}
