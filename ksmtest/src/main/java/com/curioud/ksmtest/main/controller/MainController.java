package com.curioud.ksmtest.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.curioud.ksmtest.main.service.MainService;

@Controller
public class MainController {

  MainService mainService;

  MainController(MainService mainService) {
    this.mainService = mainService;
  }
  
  /*
   * 예외 상황 발생 예상
   * 1. 데이터가 없을때
   * 2. 200을 제외한 에러가 발생했을때 대응, error status 코드 값 그대로 에러 페이지 보내기?
   * 3. ...?
   */
  @GetMapping(value = {"", "/"})
  public String mainPage(
    Model model
  ) {
    List<Map<String, Object>> boardList = mainService.getBoardList();
    model.addAttribute("boardList", boardList);
    return "main/main";
  }
}
