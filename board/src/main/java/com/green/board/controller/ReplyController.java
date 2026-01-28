package com.green.board.controller;

import com.green.board.dto.ReplyDTO;
import com.green.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards/reply")
@RequiredArgsConstructor
public class ReplyController {
  private final ReplyService replyService;

  // 댓글 목록 조회
  @GetMapping("/list/{boardNum}")
  public List<ReplyDTO> getReplyList(@PathVariable("boardNum") int boardNum) {
    return replyService.getReplyList(boardNum);
  }

  // 댓글 등록
  @PostMapping("/reg")
  public void regReply(@RequestBody ReplyDTO replyDTO) {
    replyService.regReply(replyDTO);
  }

  // 댓글 삭제
  @DeleteMapping("/delete/{replyNum}")
  public void deleteReply(@PathVariable("replyNum") int replyNum) {
    replyService.deleteReply(replyNum);
  }
}
