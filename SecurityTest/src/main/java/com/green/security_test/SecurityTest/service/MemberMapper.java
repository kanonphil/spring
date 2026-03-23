package com.green.security_test.SecurityTest.service;

import com.green.security_test.SecurityTest.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    //로그인하려는 회원 정보 조회
    MemberDTO getMemberForLogin(String memEmail);

    public void join(MemberDTO memberDTO);
}
