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

  // 게시글 목록 조회
  @GetMapping("/list")
  public List<BoardDTO> getBoardList() {
    return boardService.getBoardList();
  }

  // 게시글 등록
  @PostMapping("/reg")
  public void regBoard(@RequestBody BoardDTO boardDTO) {
    boardService.regBoard(boardDTO);
  }

  // 게시글 상세 조회
  @GetMapping("/detail/{boardNum}")
  public BoardDTO getBoard(@PathVariable("boardNum") int boardNum) {
    return boardService.getBoard(boardNum);
  }

  // 게시글 수정
  @PutMapping("/update/{boardNum}")
  public void updateBoard(@RequestBody BoardDTO boardDTO,
                          @PathVariable("boardNum") int boardNum) {
    boardDTO.setBoardNum(boardNum);
    boardService.updateBoard(boardDTO);
  }

  // 게시글 삭제
  @DeleteMapping("/delete/{boardNum}")
  public void deleteBoard(@PathVariable("boardNum") int boardNum) {
    boardService.deleteBoard(boardNum);
  }

  // 게시글 검색
  @GetMapping("/search")
  public List<BoardDTO> searchBoard(@RequestParam("searchType") String searchType,
                                    @RequestParam("keyword") String keyword) {
    return boardService.searchBoard(searchType, keyword);
  }
}
