package com.green.board.mapper;

import com.green.board.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
  // 댓글 목록 조회
  List<ReplyDTO> selectReplyList(int boardNum);
  // 댓글 등록
  void insertReply(ReplyDTO replyDTO);
  // 댓글 삭제
  void deleteReply(int replyNum);
}
