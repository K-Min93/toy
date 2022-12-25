package com.curioud.ksmtest.board.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.curioud.ksmtest.board.mapper.BoardMapper;

@Service
public class BoardService {
  
  BoardMapper boardMapper;

  BoardService(BoardMapper boardMapper) {
    this.boardMapper = boardMapper;
  }

  public Map<String, Object> getBoardDetail(
    int no
  ) {
    return boardMapper.selectOneBoardDetail(no);
  }
  
}
