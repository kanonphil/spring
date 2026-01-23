package com.green.item.service;

import com.green.item.dto.ItemDTO;
import com.green.item.mapper.ItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
  private ItemMapper itemMapper;

  // 생성자
  // @Autowired 클래스에 생성자가 하나만 존재하면 해당 생성자에 해당 어노테이션이 자동으로 붙음.
  public ItemService(ItemMapper itemMapper) {
    this.itemMapper = itemMapper;
  }

  // 전체
  public List<ItemDTO> getAllItems(ItemDTO itemDTO) {
    List<ItemDTO> getAllItems = itemMapper.getAllItems(itemDTO);
    return getAllItems;
  }

  // 번호 일치 조회
  public ItemDTO getItemByNum(int itemNum) {
    ItemDTO getItemByNum = itemMapper.getItemByNum(itemNum);
    return getItemByNum;
  }

  // 삽입
  public int regItem(ItemDTO itemDTO) {
    int regItem = itemMapper.regItem(itemDTO);
    return regItem;
  }

  // 수정
  public int updateItem(ItemDTO itemDTO) {
    int updateItem = itemMapper.updateItem(itemDTO);
    return updateItem;
  }

  // 삭제
  public int deleteItem(int itemNum) {
    int deleteItem = itemMapper.deleteItem(itemNum);
    return deleteItem;
  }
}
