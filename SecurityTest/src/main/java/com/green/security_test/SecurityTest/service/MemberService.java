package com.green.security_test.SecurityTest.service;

import com.green.security_test.SecurityTest.dto.MemberDTO;

public interface MemberService {
    //로그인하려는 회원 정보 조회
    public MemberDTO getMemberForLogin(String memEmail);

    public void join(MemberDTO memberDTO);
}
