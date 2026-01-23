package com.green.item.mapper;

import com.green.item.dto.ItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
  // 데이터 전체 조회
  List<ItemDTO> getAllItems(ItemDTO itemDTO);
  // 데이터 번호로 조회
  ItemDTO getItemByNum(int itemNum);
  // 데이터 삽입
  int regItem(ItemDTO itemDTO);
  // 데이터 수정
  int updateItem(ItemDTO itemDTO);
  // 데이터 삭제
  int deleteItem(int itemNum);
}
