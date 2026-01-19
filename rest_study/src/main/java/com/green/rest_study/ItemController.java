package com.green.rest_study;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * 상품 CRUD 요청에 대한 응답 클래스를 생성(ItemController)하고, 해당 클래스에서 요청에 대한 응답을 처리할 수 있는
 * API 기능의 메서드를 구현하시오.
 * 1) ItemController 클래스에 맴버변수로 ItemDTO가 다수 저장될 수 있는 List를 선언
 * 2) 생성자에서 List를 생성하고, 해당 List에 상품 정보 5개를 추가
 * 3) 상품 목록 조회 요청에 대한 응답 메서드(API) 구현
 * 4) 상품번호로 특정된 하나의 상품 정보를 조회 -> 응답하는 메서드(API) 구현
 * 5) 상품번호로 특정된 하나의 상품 정보를 삭제하는 메서드(API) 구현.
 *    해당 메서드는 실행결과 삭제 후 남아있는 상품목록을 응답 데이터로 리턴해야 함.
 * * 위 3가지 메서드는 postman을 이용하여 정상 실행 여부를 확인 할 것.
 */

@RestController
@RequestMapping("/items")
public class ItemController {
  // List 선언
  private List<ItemDTO> itemList;

  public ItemController() {
    itemList = new ArrayList<>();

    itemList.add(new ItemDTO(1, "상품1", 1000, "제조사1"));
    itemList.add(new ItemDTO(2, "상품2", 2000, "제조사2"));
    itemList.add(new ItemDTO(3, "상품3", 3000, "제조사3"));
    itemList.add(new ItemDTO(4, "상품4", 4000, "제조사4"));
    itemList.add(new ItemDTO(5, "상품5", 5000, "제조사5"));
  }

  // 상품 목록 조회
  @GetMapping("")
  public List<ItemDTO> getItemList() {
    System.out.println("상품 목록 조회");
    return itemList;
  }

  // 하나의 상품 조회
  @GetMapping("/{itemNum}")
  public ItemDTO getItem(@PathVariable("itemNum") int num) {
    System.out.println(num + "번 상품 조회");

    ItemDTO result = null;
    for (ItemDTO e : itemList) {
      if (e.getItemNum() == num) {
        result = e;
        break;
      }
    }

    return result;
  }

  // 삭제
  @DeleteMapping("/{itemNum}")
  public List<ItemDTO> deleteItem(@PathVariable("itemNum") int num) {
    System.out.println(num + "번 상품 삭제");

    for (int i = 0; i < itemList.size(); i++) {
      if (itemList.get(i).getItemNum() == num) {
        itemList.remove(i);
        break;
      }
    }

    return itemList;
  }

  // 상품 등록
  @PostMapping("")
  public void regItem(@RequestBody ItemDTO itemDTO) {
    System.out.println("상품 등록");
    itemList.add(itemDTO);
  }

  // 상품 수정
  @PutMapping("/{itemNum}")
  public void updateItem(
          @PathVariable("itemNum") int num,
          @RequestBody ItemDTO itemDTO) {
    System.out.println(num + "번 상품 수정");
    for (ItemDTO e : itemList) {
      if (e.getItemNum() == num) {
        e.setName(itemDTO.getName());
        e.setPrice(itemDTO.getPrice());
      }
    }
  }
}
