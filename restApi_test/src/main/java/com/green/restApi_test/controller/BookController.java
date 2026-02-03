package com.green.restApi_test.controller;

import com.green.restApi_test.dto.BookDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
  private List<BookDTO> bookList;

  public BookController() {
    // 책 목록이 저장될 List 생성
    bookList = new ArrayList<>();

    // 리스트에 게시글을 5개 저장
    bookList.add(new BookDTO(1, "책1", "김자바", "java", 1000));
    bookList.add(new BookDTO(2, "책2", "이자바", "python", 2000));
    bookList.add(new BookDTO(3, "책3", "김자바", "html", 3000));
    bookList.add(new BookDTO(4, "책4", "이자바", "react", 4000));
    bookList.add(new BookDTO(5, "책5", "김자바", "database", 5000));
  }

  // 모든 도서정보 조회
  @GetMapping("")
  public List<BookDTO> getBookList() {
    System.out.println("모든 도서정보 조회");
    return bookList;
  }

  // 하나의 도서정보 조회
  @GetMapping("/{bookNum}")
  public BookDTO getBook(@PathVariable("bookNum") int bookNum) {
    System.out.println(bookNum + "번 도서정보를 조회합니다.");

    BookDTO result = null;
    for (BookDTO e : bookList) {
      if (e.getBookNum() == bookNum) {
        result = e;
        break;
      }
    }

    return result;
  }

  // 하나의 도서정보를 등록 (도서명, 저자, 도서내용, 가격)
  @PostMapping("")
  public void regBook(@RequestBody BookDTO bookDTO) {
    System.out.println("도서정보를 등록합니다.");
    bookList.add(bookDTO);
  }

  // 하나의 도서정보를 삭제
  @DeleteMapping("/{bookNum}")
  public List<BookDTO> deleteBook(@PathVariable("bookNum") int bookNum) {
    System.out.println("도서 정보를 삭제합니다. 삭제하려는 글 번호: " + bookNum);

    for (int i = 0; i < bookList.size(); i++) {
      if (bookList.get(i).getBookNum() == bookNum) {
        bookList.remove(i);
        break;
      }
    }

    return bookList;
  }

  // 하나의 도서정보에서 도서명, 저자, 도서가격 수정
  @PutMapping("/{bookNum}")
  public void updateBook(@RequestBody BookDTO bookDTO,
                         @PathVariable("bookNum") int bookNum) {
    System.out.println("도서 정보를 수정합니다. 수정하려는 글 번호: " + bookNum);
    for (BookDTO e : bookList) {
      if (e.getBookNum() == bookNum) {
        e.setBookTitle(bookDTO.getBookTitle());
        e.setAuthor(bookDTO.getAuthor());
        e.setBookPrice(bookDTO.getBookPrice());
      }
    }
  }
}
