package com.green.board.service;

import com.green.board.dto.BoardDTO;
import com.green.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardMapper boardMapper;

  // 게시글 목록 조회
  public List<BoardDTO> getBoardList() {
    return boardMapper.getBoardList();
  }

  // 게시글 등록
  public void regBoard(BoardDTO boardDTO) {
    boardMapper.regBoard(boardDTO);
  }

  // 게시글 상세 조회 + 조회수 증가
  @Transactional
  public BoardDTO getBoard(int boardNum, boolean increaseReadCnt) {
    // 조회수 증가
    if (increaseReadCnt) {
      boardMapper.updateReadCnt(boardNum);
    }
    // 게시글 조회
    return boardMapper.getBoard(boardNum);
  }

  // 게시글 수정
  public void updateBoard(BoardDTO boardDTO) {
    boardMapper.updateBoard(boardDTO);
  }

  // 게시글 삭제
  public void deleteBoard(int boardNum) {
    boardMapper.deleteBoard(boardNum);
  }

  // 게시글 검색
  public List<BoardDTO> searchBoard(String searchType, String keyword) {
    return boardMapper.searchBoard(searchType, keyword);
  }
}
