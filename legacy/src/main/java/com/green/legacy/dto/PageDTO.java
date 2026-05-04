package com.green.legacy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
  private int nowPage;        // 현재 선택된 페이지 번호
  private int totalDataCnt;   // 전체 데이터 수
  private int displayDataCnt; // 한 페이지에 보여질 데이터 수
  private int beginPage;      // 화면에 보이는 첫 번째 페이지 번호
  private int endPage;        // 화면에 보이는 마지막 페이지 번호
  private int displayPageCnt; // 화면에 보여지는 페이지 수
  private boolean prev;       // 이전 버튼의 유무
  private boolean next;       // 다음 버튼의 유무
  private int offsetCnt;      // 조회 쿼리에 사용할 offset

  public PageDTO() {
    nowPage = 1;
    displayDataCnt = 10;
    displayPageCnt = 5;
  }

  // 페이지와 관련된 데이터를 세팅하는 메서드
  public void setPageInfo() {
    // 화면에 보이는 마지막 페이지 번호
    // 한 페이지에 보이는 페이지 수 * 올림(현재페이지 / 한 페이지에 보이는 페이지 수)
    endPage = displayPageCnt * (int) Math.ceil((double) nowPage / displayPageCnt);

    // 화면에 보이는 첫 번째 페이지 번호
    // 화면에 보이는 마지막 페이지 번호 - 화면에 보이는 페이지 수 + 1
    beginPage = endPage - displayPageCnt + 1;

    // 전체 페이지 수
    int totalPageCnt = (int) Math.ceil((double) totalDataCnt / displayDataCnt);

    // next 버튼 유무
    if (endPage < totalPageCnt) {
      next = true;
    } else {
      next = false;
      endPage = totalPageCnt;
    }

    // prev 버튼 유무
    prev = beginPage == 1 ? false : true;

    // 데이터 수가 0일 경우
    if (totalDataCnt == 0) {
      endPage = 1;
    }

    // offset 세팅
    offsetCnt = (nowPage - 1) * displayDataCnt;
  }
}
