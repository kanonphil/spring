package com.green.board.controller;

import com.green.board.dto.BoardDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @GetMapping("/test1")
  public ResponseEntity<String> test1() {
    String name = "kim";
    return ResponseEntity.status(HttpStatus.OK).body(name);
  }

  @GetMapping("/test2")
  public ResponseEntity<BoardDTO> test2() {
    BoardDTO dto = new BoardDTO();
    dto.setBoardNum(1);
    dto.setTitle("제목");
    dto.setWriter("작성자");
    dto.setContent("내용");

    // 헤더에 넣을 데이터
    HttpHeaders headers = new HttpHeaders();
    headers.add("myName", "kim");
    headers.add("myAge", "30");

    return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(dto);
  }

  @GetMapping("/test3")
  public ResponseEntity<?> test3() {
    System.out.println("기능 성공");
    // return 할 데이터가 없으면 build() 메서드를 마지막에 호출
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
