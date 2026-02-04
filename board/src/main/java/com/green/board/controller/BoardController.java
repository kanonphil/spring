package com.green.board.controller;

import com.green.board.dto.BoardDTO;
import com.green.board.service.BoardService;
import com.sun.jdi.event.ExceptionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
  private final BoardService boardService;

  // 게시글 목록 조회 (검색 포함)
  @GetMapping("")
  public ResponseEntity<List<BoardDTO>> getBoardList(
          @RequestParam(required = false) String searchType,
          @RequestParam(required = false) String keyword) {
    try {
      log.info("게시글 목록 조회 요청 - searchType: {}, keyword: {}", searchType, keyword);
      List<BoardDTO> boardList = boardService.getBoardList(searchType, keyword);
      log.info("게시글 {}건 요청 완료", boardList.size());
      return ResponseEntity.ok(boardList);
    } catch (Exception e) {
      log.error("게시글 목록 조회 중 오류 발생 - searchType: {}, keyword: {}", searchType, keyword);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 게시글 등록
  @PostMapping("")
  public ResponseEntity<Integer> regBoard(@RequestBody BoardDTO boardDTO) {
    try {
      log.info("게시글 등록 요청");
      int result = boardService.regBoard(boardDTO);
      log.info("게시글 등록 요청 완료");
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (Exception e) {
      log.error("게시글 등록 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 게시글 상세 조회 (조회수 증가)
  @GetMapping("/{boardNum}")
  public ResponseEntity<BoardDTO> getBoard(@PathVariable("boardNum") int boardNum) {
    try {
      BoardDTO board = boardService.getBoard(boardNum);

      if (board == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }

      return ResponseEntity.ok(board);
    } catch (Exception e) {
      log.error("게시글 조회 중 오류 발생 - boardNum: {}", boardNum, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 게시글 수정용 조회 (조회수 증가 X)
  @GetMapping("/{boardNum}/edit")
  public ResponseEntity<BoardDTO> getBoardForEdit(@PathVariable("boardNum") int boardNum) {
    try {
      BoardDTO board = boardService.getBoardForEdit(boardNum);

      if (board == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }

      return ResponseEntity.ok(board);
    } catch (Exception e) {
      log.error("게시글 조회 중 오류 발생 (수정용) - boardNum: {}", boardNum, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 게시글 수정
  @PutMapping("/{boardNum}")
  public ResponseEntity<Integer> updateBoard(@RequestBody BoardDTO boardDTO,
                                             @PathVariable("boardNum") int boardNum) {
    try {
      log.info("게시글 수정 요청 - boardNum: {}", boardNum);
      boardDTO.setBoardNum(boardNum);
      int result = boardService.updateBoard(boardDTO);

      if (result >= 1) {
        log.info("게시글 수정 성공 - boardNum: {}", boardNum);
        return ResponseEntity.ok().body(result);
      } else {
        log.warn("게시글 수정 실패 - boardNum: {}, result: {}", boardNum, result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
      }
    } catch (Exception e) {
      log.error("게시글 수정 중 오류 발생 - boardNum: {}", boardNum, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
    }
  }

  // 게시글 삭제
  @DeleteMapping("/{boardNum}")
  public ResponseEntity<Integer> deleteBoard(@PathVariable("boardNum") int boardNum) {
    try {
      log.info("게시글 삭제 요청 - boardNum: {}", boardNum);
      int result = boardService.deleteBoard(boardNum);

      if (result >= 1) {
        log.info("게시글 삭제 성공 - boardNum: {}", boardNum);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
      } else {
        log.warn("게시글 삭제 실패 - boardNum: {}, result: {}", boardNum, result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
      }
    } catch (Exception e) {
      log.error("게시글 삭제 중 오류 발생 - boardNum: {}", boardNum, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
    }
  }
}
