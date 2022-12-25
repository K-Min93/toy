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

  @GetMapping(value = {"", "/"})
  public String mainPage(
    Model model
  ) {
    List<Map<String, Object>> boardList = mainService.getBoardList();
    model.addAttribute("boardList", boardList);
    return "main/main";
  }
}
