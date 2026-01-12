package com.green.test;

import org.springframework.stereotype.Component;

interface Cook {
  void cook();
}

@Component("a")  // 객체 생성 어노테이션
public class Chef implements Cook {
  public void cook() {
    System.out.println("요리 중");
  }
}

class KoreanChef implements Cook {
  public void cook() {
    System.out.println("학식 요리 중");
  }
}
