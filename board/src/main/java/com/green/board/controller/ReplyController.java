package com.green.board.controller;

import com.green.board.dto.ReplyDTO;
import com.green.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {
  private final ReplyService replyService;

  // 댓글 목록 조회
  @GetMapping("/board/{boardNum}")
  public ResponseEntity<List<ReplyDTO>> getReplyList(@PathVariable("boardNum") int boardNum) {
    List<ReplyDTO> replies = replyService.getReplyList(boardNum);
    return ResponseEntity.ok(replies);
  }

  // 댓글 등록
  @PostMapping("")
  public ResponseEntity<Void> regReply(@RequestBody ReplyDTO replyDTO) {
    replyService.regReply(replyDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  // 댓글 삭제
  @DeleteMapping("/{replyNum}")
  public ResponseEntity<Void> deleteReply(@PathVariable("replyNum") int replyNum) {
    replyService.deleteReply(replyNum);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
