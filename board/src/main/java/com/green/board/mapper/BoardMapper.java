package com.green.board.mapper;

import com.green.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
  // 전체조회 + 게시글 검색
  List<BoardDTO> selectBoardList(
          @Param("searchType") String searchType,
          @Param("keyword") String keyword
  );
  // 게시글등록
  int insertBoard(BoardDTO boardDTO);
  // 게시글 상세 조회
  BoardDTO selectBoard(int boardNum);
  // 게시글 수정
  int updateBoard(BoardDTO boardDTO);
  // 게시글 삭제
  int deleteBoard(int boardNum);
  // 조회수 증가
  void updateReadCnt(int boardNum);
  // 게시글 검색
//  List<BoardDTO> selectBoardBySearch(String searchType, String keyword);
}
