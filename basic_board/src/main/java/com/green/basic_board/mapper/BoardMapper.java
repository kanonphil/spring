package com.green.basic_board.mapper;

import com.green.basic_board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper // 객체생성 + xml 파일에서 만든 쿼리문을 실행시키는 interface라는 것을 지정
public interface BoardMapper {
  // 메서드명은 반드시 xml 파일에서 정의한 쿼리의 id와 일치
  // 메서드의 리턴타입 : 쿼리 실행 결과 전체 데이터를 담을 수 있는 자료형
  // -> select : 조회되는 데이터에 따라 리턴 타입이 달라짐
  // -> insert, delete, update : void or int
  // 메서드의 매개변수 : 쿼리 실행 시 채워줘야 하는 데이터

  String test1();
  List<Integer> test2();
  BoardDTO test3(int boardNum);
  List<BoardDTO> test4(BoardDTO boardDTO);
  int insertBoard(BoardDTO boardDTO);
  int deleteBoard(int boardNum);
  int updateBoard(BoardDTO boardDTO);

  List<BoardDTO> getBoard(BoardDTO boardDTO);
}
