package com.green.legacy.controller;

import com.green.legacy.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
  @RequestMapping("/get-order")
  public String getOrder() {
    return "order";
  }

  @RequestMapping("/delivery")
  public String goDelivery(@ModelAttribute OrderDTO dto, Model model) {
    model.addAttribute("order", dto);
    return "delivery";
  }

  @RequestMapping("/complete")
  public String complete(@ModelAttribute OrderDTO dto, Model model) {

    int price = 20000 * dto.getQuantity();

    if (dto.getOptions() != null) {
      price += dto.getOptions().size() * 1000;
    }

    dto.setTotalPrice(price);

    model.addAttribute("order", dto);
    return "order_info";
  }
}
