package com.green.jpa_study.service;

import com.green.jpa_study.dto.MemberRequest;
import com.green.jpa_study.dto.MemberResponse;
import com.green.jpa_study.entity.Member;
import com.green.jpa_study.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  // 회원 목록 조회
  public List<MemberResponse> findAll() {
    // return 타입: List<Member>
//    List<Member> list = memberRepository.findAll();

    // List<Member> 타입을 List<MemberResponse>로 변환
//    List<MemberResponse> responseList = list
//            .stream()
//            .map(MemberResponse::from)
//            .toList();

    return memberRepository.findAll()
            .stream()
            .map(MemberResponse::from)
            .toList();
  }

  // 게시글 삽입
  @Transactional
  public MemberResponse create(MemberRequest memberRequest) {

    Member m = new Member();
    m.setMemEmail(memberRequest.getMemEmail());
    m.setMemName(memberRequest.getMemName());
    m.setMemAge(memberRequest.getMemAge());

    Member result = memberRepository.save(m);
    return MemberResponse.from(result);
  }

  // 게시글 상세 보기
  public MemberResponse findOne(Long memNum) {
    Member m = memberRepository.findById(memNum).get();
    return MemberResponse.from(m);
  }

  // 게시글 수정
  public MemberResponse update(Long memNum, MemberRequest memberRequest) {
    Member m = memberRepository.findById(memNum).get();

    // Entity의 값을 변경하면 테이블의 값을 변경하는 것과 같기 대문에 Update쿼리 실행
    m.setMemName(memberRequest.getMemName());
    m.setMemAge(memberRequest.getMemAge());

    return MemberResponse.from(m);
  }

  // 게시글 삭제

  //
  public List<MemberResponse> findByMemNameAndMemAge(MemberRequest memberRequest) {
    List<Member> ml = memberRepository.findByMemNameAndMemAge(memberRequest.getMemName(), memberRequest.getMemAge());
    return ml.stream().map(MemberResponse::from).toList();
  }
}
