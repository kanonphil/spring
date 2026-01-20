package com.green.rest_study;

import lombok.*;

/*
* 사원 정보를 나타내는 Emp 클래스 생성
* 사원정보는 사번, 사원명, 부서명, 급여, 직급이 있음.
* 해당 정보를 표현하는 맴버변수 및 기본 생성자, 모든 맴버변수를 초기화할 수 있는 매개변수를 가진 생성자,
* getter, setter, toString 메서드를 lombok 어노테이션을 이용하여 생성.
*
* 사원과 관련된 CRUD API를 제공하는 EmpController를 생성하여 요청에 대한 응답처리 메서드 구현.
* 1) EmpController 클래스는 Emp 객체를 다수 저장할 수 있는 List를 맴버변수로 가짐.
* 2) 생성자에서는 List를 생성하고, 해당 List에 Emp 객체를 5개 저장
* 3) 사원 목록 조회 요청에 응답하는 api 메서드 구현
* 4) 사번을 통해 특정 사원 한 명의 정보를 요청하면 이에 응답하는 api 메서드 구현
* 5) 사원 등록 요청에 응답하는 api메서드 구현. 요청 시 등록할 사원정보가 함께 전달 됨
* 6) 사번을 통해 특정 사원 한 명을 삭제하는 요청이 오면 이에 응답하는 api 메서드 구현
* 7) 사번을 통해 특정 사원 한 명의 정보 수정 요청이 오면 이에 응답하는 api 메서드 구현
*    요청 시 정보를 수정하고자 하는 사번, 급여, 부서명 정보가 함께 전달 됨
* 8) postman을 활용하여 위에서 만든 메서드의 동작을 확인
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {
  private Integer empNo;
  private String name;
  private String dept;
  private int salary;
  private String position;
}
