package com.green.board.controller;

import com.green.board.dto.BoardDTO;
import com.green.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
  private final BoardService boardService;

  // 게시글 목록 조회 (검색 포함)
  @GetMapping("")
  public List<BoardDTO> getBoardList(
          @RequestParam(required = false) String searchType,
          @RequestParam(required = false) String keyword) {
    return boardService.getBoardList(searchType, keyword);
  }

  // 게시글 등록
  @PostMapping("")
  public void regBoard(@RequestBody BoardDTO boardDTO) {
    boardService.regBoard(boardDTO);
  }

  // 게시글 상세 조회 (조회수 증가)
  @GetMapping("/{boardNum}")
  public BoardDTO getBoard(@PathVariable("boardNum") int boardNum) {
    return boardService.getBoard(boardNum);
  }

  // 게시글 상세 조회 (조회수 증가)
  @GetMapping("/{boardNum}/edit")
  public BoardDTO getBoardForEdit(@PathVariable("boardNum") int boardNum) {
    return boardService.getBoardForEdit(boardNum);
  }

  // 게시글 수정
  @PutMapping("/{boardNum}")
  public void updateBoard(@RequestBody BoardDTO boardDTO,
                          @PathVariable("boardNum") int boardNum) {
    boardDTO.setBoardNum(boardNum);
    boardService.updateBoard(boardDTO);
  }

  // 게시글 삭제
  @DeleteMapping("/{boardNum}")
  public void deleteBoard(@PathVariable("boardNum") int boardNum) {
    boardService.deleteBoard(boardNum);
  }
}
