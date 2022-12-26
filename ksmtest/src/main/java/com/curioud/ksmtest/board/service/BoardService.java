package com.curioud.ksmtest.board.service;

import java.util.List;
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

  public List<Map<String, Object>> getComments(
    int boardNo
  ) {
    return boardMapper.selectAllComment(boardNo);
  }

  public void createBoard(
    String subject,
    String content
  ) {
    boardMapper.insertBoard(subject, content);
  }
  
  public void modifyBoard(
    int no,
    String subject,
    String content
  ) {
    boardMapper.updateBoard(no, subject, content);
  }

}
