package com.curioud.ksmtest.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.curioud.ksmtest.board.mapper.BoardMapper;
import com.curioud.ksmtest.util.FileUtil;

@Service
public class BoardService {
  
  BoardMapper boardMapper;

  FileUtil fileUtil;

  BoardService(BoardMapper boardMapper) {
    this.boardMapper = boardMapper;
  }

  public Map<String, Object> getBoardDetail(
    int no
  ) throws Exception {
    Map<String, Object> board = boardMapper.selectOneBoardDetail(no);
    
    if (board == null) {
      throw new Exception();
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
    int boardNo
  ) {
    boardMapper.deleteBoard(boardNo);
  }

  public void createThumnail(
    int boardNo,
    MultipartFile file
  ) throws IOException {
    if (!file.isEmpty()) {
      /* TODO
        수정페이지에서
        1.파일 검사
        (
        용량 - (1KB = 1024byte, 1MB = 1024kb),
        확장자 - jpg, jpge, png. bmp,
        )
        2.파일 업로드, 로컬경로설정(상대 경로)
        3. 파일에 대한 데이터 저장
      */
      String originalFilename = file.getOriginalFilename();
      String extension = fileUtil.getExtension(originalFilename);
      long fileSize = file.getSize();

      if (fileUtil.validateFileSize(fileSize) && fileUtil.validateFileExtension(extension)) {
        String filename = fileUtil.makeNewFilename(extension);
        String uploadFileLocation = "D:\\toy\\ksmtest\\src\\main\\resources\\static\\img\\";
        String upload = uploadFileLocation + filename;

        file.transferTo(new File(upload));

        boardMapper.updateThumnail(boardNo, filename);
      
      } else {
        // 용량 및 확장자 에러

      }
    } else {
      // 첨부파일 없음
      
    }
  }
}
