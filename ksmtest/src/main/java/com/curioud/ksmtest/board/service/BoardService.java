package com.curioud.ksmtest.board.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.curioud.ksmtest.board.mapper.BoardMapper;
import com.curioud.ksmtest.util.DateUtil;
import com.curioud.ksmtest.util.FileUtil;

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

  public String createThumnail(
    int boardNo,
    MultipartFile file
  ) throws IOException {
    if (!file.isEmpty()) {
      /* TODO
        1.파일 검사
        (
          용량 - (1KB = 1024byte, 1MB = 1024kb),
          확장자 - jpg, jpge, png. bmp,
        )
        2.파일 업로드, 로컬경로설정(상대 경로)
        3. 파일에 대한 데이터 저장
      */
        
      FileUtil fileUtil = new FileUtil();
      DateUtil dateUtil = new DateUtil();
      
      long fileSize = file.getSize();
      String mimeType = fileUtil.getMimeType(file);

      if (fileUtil.isFileSize(fileSize) && fileUtil.isFileMimeType(mimeType)) {
        String extension = fileUtil.getExtension(mimeType);
        String newFilename = fileUtil.makeNewFilename() + "." + extension;
        String date = dateUtil.getZoneTime();
        date = dateUtil.getZoneTimeDateFormat(date);

        // img폴더 아래 날짜별로 업로드파일 구분 (디렉토리 생성)
        if (fileUtil.isDirectoryCheck(date)) {
          fileUtil.makeDirectory(date);
        }

        fileUtil.uploadFile(file, date, newFilename);
        newFilename = date + "/" + newFilename;
        boardMapper.updateThumnail(boardNo, newFilename);
        
        return newFilename;
      } else {
        // 용량 및 확장자 에러
        return null;
      }
    } else {
      // 첨부파일 없음
      return null;
    }
  }
}
