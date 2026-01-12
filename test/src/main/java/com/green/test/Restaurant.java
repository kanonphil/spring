package com.green.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Restaurant {
  private Chef chef;

  // 생성자 의존성 주입
  @Autowired  // 생성된 객체 중 자료형이 일치하는 객체를 매개변수로 주입시켜주세요
  public Restaurant(Chef chef) {
    this.chef = chef;
  }

  public void run() {
    chef.cook();
  }
}
