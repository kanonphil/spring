package com.green.legacy.service;

import com.green.legacy.dto.ReplyDTO;
import com.green.legacy.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
  private final ReplyMapper replyMapper;

  public List<ReplyDTO> selectReplyList(int boardNum) {
    return replyMapper.selectReplyList(boardNum);
  }

  public void insertReply(ReplyDTO replyDTO) {
    replyMapper.insertReply(replyDTO);
  }

  public void deleteReply(int replyNum) {
    replyMapper.deleteReply(replyNum);
  }
}
