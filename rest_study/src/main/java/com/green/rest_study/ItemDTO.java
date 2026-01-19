package com.green.rest_study;

import lombok.*;

/*
  상품 하나의 정보를 저장할 수 있는 자료형인 ItemDTO 클래스를 생성
  상품은 상품번호, 상품명, 가격, 제조사 정보를 가짐
  위 정보를 가질 수 있도록 맴버변수를 만들고 추가적으로
  기본 생성자, 매개변수로 모든 맴버변수를 초기화하는 생성자,
  getter, setter, toString 메서드를 구현
  단, lombok 라이브러리를 활용
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
  private Integer itemNum;
  private String name;
  private Integer price;
  private String manu;
}
