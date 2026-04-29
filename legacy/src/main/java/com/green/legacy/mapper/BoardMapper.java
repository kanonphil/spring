package com.green.legacy.mapper;

import com.green.legacy.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
  List<BoardDTO> selectBoardList();
  void insertBoard(BoardDTO boardDTO);
  BoardDTO selectBoardDetail(@Param("boardNum") int boardNum);
}
