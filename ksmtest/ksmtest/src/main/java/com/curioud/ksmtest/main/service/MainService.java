package com.curioud.ksmtest.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.curioud.ksmtest.main.mapper.MainMapper;

@Service
public class MainService {

  MainMapper mainMapper;

  MainService(MainMapper mainMapper) {
    this.mainMapper = mainMapper;
  }

  public List<Map<String, Object>> getBoardList() {
    return mainMapper.selectBoardList();
  }
}
