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

  // 게시글 목록 조회 (검색 포함)
  public List<BoardDTO> getBoardList(String searchType, String keyword) {
    // 검색 조건이 있으면 검색, 없으면 전체 목록
//    if (keyword != null && !keyword.trim().isEmpty()) {
//      return boardMapper.selectBoardBySearch(searchType, keyword);
//    }
    return boardMapper.selectBoardList(searchType, keyword);
  }

  // 게시글 등록
  public int regBoard(BoardDTO boardDTO) {
    return boardMapper.insertBoard(boardDTO);
  }

  // 게시글 상세 조회 (조회수 증가 o)
  @Transactional
  public BoardDTO getBoard(int boardNum) {
    boardMapper.updateReadCnt(boardNum);
    return boardMapper.selectBoard(boardNum);
  }

  // 게시글 수정용 조회 (조회수 증가 x)
  public BoardDTO getBoardForEdit(int boardNum) {
    return boardMapper.selectBoard(boardNum);
  }

  // 게시글 수정
  public int updateBoard(BoardDTO boardDTO) {
    return boardMapper.updateBoard(boardDTO);
  }

  // 게시글 삭제
  public int deleteBoard(int boardNum) {
    return boardMapper.deleteBoard(boardNum);
  }
}
