package com.green.board.service;

import com.green.board.dto.ReplyDTO;
import com.green.board.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
  private final ReplyMapper replyMapper;

  // 댓글 목록 조회
  public List<ReplyDTO> getReplyList(int boardNum) {
    return replyMapper.selectReplyList(boardNum);
  }

  // 댓글 등록
  public void regReply(ReplyDTO replyDTO) {
    replyMapper.insertReply(replyDTO);
  }

  // 댓글 삭제
  public void deleteReply(int replyNum) {
    replyMapper.deleteReply(replyNum);
  }
}
