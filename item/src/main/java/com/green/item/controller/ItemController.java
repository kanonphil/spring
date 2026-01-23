package com.green.item.controller;

import com.green.item.dto.ItemDTO;
import com.green.item.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
  private ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("")
  public List<ItemDTO> getAllItems() {
    List<ItemDTO> getAllItems = itemService.getAllItems();
    return getAllItems;
  }

  @GetMapping("/{itemNum}")
  public ItemDTO getItemByNum(@PathVariable int itemNum) {
    ItemDTO getItemByNum = itemService.getItemByNum(itemNum);
    return getItemByNum;
  }

  @PostMapping("")
  public int regItem(@RequestBody ItemDTO itemDTO) {
    int regItem = itemService.regItem(itemDTO);
    return regItem;
  }

  @PutMapping("/{itemNum}")
  public int updateItem(@PathVariable int itemNum,
                        @RequestBody ItemDTO itemDTO) {
    itemDTO.setItemNum(itemNum);
    int updateItem = itemService.updateItem(itemDTO);
    return updateItem;
  }

  @DeleteMapping("/{itemNum}")
  public int deleteItem(@PathVariable int itemNum) {
    int deleteItem = itemService.deleteItem(itemNum);
    return deleteItem;
  }
}
