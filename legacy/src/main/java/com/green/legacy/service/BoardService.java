package com.green.legacy.service;

import com.green.legacy.dto.BoardDTO;
import com.green.legacy.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardMapper boardMapper;

  public List<BoardDTO> selectBoardList() {
    return boardMapper.selectBoardList();
  }

  public void regBoard(BoardDTO boardDTO) {
    boardMapper.insertBoard(boardDTO);
  }

  public BoardDTO selectBoardDetail(int boardNum) {
    return boardMapper.selectBoardDetail(boardNum);
  }
}
