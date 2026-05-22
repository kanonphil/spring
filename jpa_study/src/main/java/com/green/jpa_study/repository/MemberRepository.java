package com.green.jpa_study.repository;

import com.green.jpa_study.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// MyBatis의 Mapper Interface 역할
// DB 조작 기능을 위해서는 JpaRepository 인터페이스를 상속받는다.
// JpaRepository<Entity명, PK자료형>
// JpaRepository 인터베이스만 상속받으면, 어떠한 코드를 작성하지 않으면 이미 CRUD는 완성
public interface MemberRepository extends JpaRepository<Member, Long> {
  // findAll(): 전체목록조회
  // findById(pk): 상세조회
  // count(): 데이터 갯수 조회
  // delete(pk): 삭제
  // save(): 저장 + 업데이트

  // 기본이 아닌 쿼리문을 실행하려면 메서드를 추가 선언
  // 메서드명에 따라 쿼리문이 자동 생성

  // 1. 이름을 조건으로 회원 조회
  List<Member> findByMemName(String memName);

  // 2. 나이를 조건으로 회원 조회
  List<Member> findByMemAge(Integer memAge);

  // 3. 이름과 나이를 조건으로 회원 조회
  List<Member> findByMemNameAndMemAge(String memName, Integer memAge);

  // 4. 크기 비교 조건
  // 크다: GreaterThan
  // 작다: LessThan
  List<Member> findByMemAgeGreaterThanEqual();

  // 5. WHERE NAME = ? AND AGE >= ?
  List<Member> findByMemNameAndMemAgeGreaterThanEqual(String memName, Integer memAge);

  // 6. WHERE MEM_NAME LIKE '%???%'
  List<Member> findByMemNameContaining(String memName);

  // 7. ORDER BY CREATE_AT DESC
  List<Member> findAllOrderByCreateAtDesc();

  // 8. WHERE MEM_EMAIL LIKE '%??%' ORDER BY MEM_NAME ASC;
  List<Member> findByMemEmailContainingOrderByMemName();

  // 10.
  @Query(value = "SELECT * FROM JPA_MEMBER WHERE MEM_AGE >= :memAge", nativeQuery = true)
  List<Member> select1(@Param("memAge") Integer memAge);

}
