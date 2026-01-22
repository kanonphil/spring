package com.green.basic_board.controller;

import com.green.basic_board.dto.BoardDTO;
import com.green.basic_board.service.BoardService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/boards")
//@RequiredArgsConstructor
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/test1")
    public String test1() {
        String result = boardService.runTest1();
        return result;
    }

    @GetMapping("/test2")
    public List<Integer> test2() {
        List<Integer> result = boardService.runTest2();
        return result;
    }

    @GetMapping("/test3/{boardNum}")
    public BoardDTO test3(@PathVariable int boardNum) {
        BoardDTO result = boardService.runTest3(boardNum);
        return result;
    }

    @GetMapping("/test4")
    public List<BoardDTO> test4(BoardDTO boardDTO) {
        List<BoardDTO> result = boardService.runTest4(boardDTO);
        return result;
    }
}
