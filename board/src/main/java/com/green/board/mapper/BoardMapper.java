package com.green.board.mapper;

import com.green.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
  // 전체조회
  List<BoardDTO> getBoardList();
  // 게시글등록
  void regBoard(BoardDTO boardDTO);
  // 게시글 상세 조회
  BoardDTO getBoard(int boardNum);
  // 게시글 수정
  void updateBoard(BoardDTO boardDTO);
  // 게시글 삭제
  void deleteBoard(int boardNum);
  // 조회수 증가
  void updateReadCnt(int boardNum);
  // 게시글 검색
  List<BoardDTO> searchBoard(String searchType, String keyword);
}
