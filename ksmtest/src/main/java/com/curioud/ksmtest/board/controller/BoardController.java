package com.curioud.ksmtest.board.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curioud.ksmtest.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
  
  BoardService boardService;

  BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping("/write")
  public String boardWritePage() {
    return "board/write";
  }

  @GetMapping("/detail/{no}")
  public String boardDetailPage(
    @PathVariable("no") int no,
    Model model
  ) {
    Map<String, Object> board = boardService.getBoardDetail(no);
    model.addAttribute("board", board);
    return "board/detail";
  }
}
