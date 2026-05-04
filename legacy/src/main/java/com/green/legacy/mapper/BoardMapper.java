package com.green.legacy.mapper;

import com.green.legacy.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
  List<BoardDTO> selectBoardList(BoardDTO boardDTO);
  void insertBoard(BoardDTO boardDTO);
  BoardDTO selectBoardDetail(@Param("boardNum") int boardNum);
  void deleteBoard(@Param("boardNum") int boardNum);
  void updateBoard(BoardDTO boardDTO);

  int selectBoardCnt();
}
