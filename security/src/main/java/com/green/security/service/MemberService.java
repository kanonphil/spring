package com.green.security.service;

import com.green.security.dto.MemberDTO;
import com.green.security.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberMapper memberMapper;
  private final PasswordEncoder passwordEncoder;

  // 회원가입
  public void join(MemberDTO memberDTO) {
    // 입력한 비번 암호화
    String encodingPw = passwordEncoder.encode(memberDTO.getMemPw());
    memberDTO.setMemPw(encodingPw);

    memberMapper.join(memberDTO);
  }

  public MemberDTO selectLoginInfo(String memEmail) {
    return memberMapper.selectLoginInfo(memEmail);
  }
}
