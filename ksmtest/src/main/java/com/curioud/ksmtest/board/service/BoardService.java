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
  ) throws Exception {
    Map<String, Object> board = boardMapper.selectOneBoardDetail(no);
    
    if (board == null) {
      throw new Exception("데이터 없음");
    }

    return board;
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

  public void createComment(
    int boardNo,
    String comment
  ) {
    boardMapper.insertComment(boardNo, comment);
  }

  public void deleteBoard(
    int no
  ) {
    boardMapper.deleteBoard(no);
  }
}
