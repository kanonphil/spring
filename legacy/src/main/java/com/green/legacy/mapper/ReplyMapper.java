package com.green.legacy.mapper;

import com.green.legacy.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
  List<ReplyDTO> selectReplyList(@Param("boardNum") int boardNum);
  void insertReply(ReplyDTO replyDTO);
  void deleteReply(@Param("replyNum") int replyNum);
}
