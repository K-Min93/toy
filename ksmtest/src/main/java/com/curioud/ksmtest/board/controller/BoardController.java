package com.curioud.ksmtest.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curioud.ksmtest.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
  
  BoardService boardService;

  BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping("/write")
  public String writeBoardPage() {
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

  @ResponseBody
  @GetMapping("/api/comments/{no}")
  public List<Map<String, Object>> getComments(
    @PathVariable("no") int boardNo
  ) {
    return boardService.getComments(boardNo);
  }

  @PostMapping("/do/crete-board")
  public String createBoard(
    @RequestParam("subject") String subject,
    @RequestParam("content") String content
  ) {

    // TODO : insert 예외 발생 상황 생각해서 처리하기
    boardService.createBoard(subject, content);

    return "redirect:/";
  }

  @GetMapping("/modify/{no}")
  public String modifyBoardPage(
    @PathVariable("no") int no,
    Model model
  ) {

    Map<String, Object> board = boardService.getBoardDetail(no);
    
    model.addAttribute("board", board);
    return "board/modify";
  }

  @PostMapping("/do/modify-board")
  public String modifyBoard(
    @RequestParam("no") int no,
    @RequestParam("subject") String subject,
    @RequestParam("content") String content
  ) {

    // TODO : update 예외 발생 상황 생각해서 처리하기
    boardService.modifyBoard(no, subject, content);

    return "redirect:/";
  }
  
  @ResponseBody
  @PutMapping("/api/do/create-comment/{boardNo}")
  public void createComment(
    @PathVariable("boardNo") int boardNo,
    @RequestBody Map<String, Object> comment
  ) {
    System.out.println("break point");
    System.out.println(comment.get("comment"));

    // TODO : insert 예외 발생 상황 생각해서 처리하기
    boardService.createComment(boardNo, (String) comment.get("comment"));
  }
}
