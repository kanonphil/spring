package com.green.security_test.SecurityTest.service;

import com.green.security_test.SecurityTest.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;

    //로그인하려는 회원 정보 조회
    public MemberDTO getMemberForLogin(String memEmail){
        return memberMapper.getMemberForLogin(memEmail);
    }

    @Override
    public void join(MemberDTO memberDTO) {
        memberMapper.join(memberDTO);
    }
}
