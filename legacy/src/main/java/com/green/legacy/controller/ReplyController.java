package com.green.legacy.controller;

import com.green.legacy.dto.ReplyDTO;
import com.green.legacy.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {
  private final ReplyService replyService;

  @RequestMapping("/write")
  public String write(ReplyDTO replyDTO) {
    replyService.insertReply(replyDTO);
    return "redirect:/boards/detail?boardNum=" + replyDTO.getBoardNum();
  }

  @RequestMapping("/delete")
  public String delete(int replyNum, int boardNum) {
    replyService.deleteReply(replyNum);
    return "redirect:/boards/detail?boardNum=" + boardNum;
  }
}
