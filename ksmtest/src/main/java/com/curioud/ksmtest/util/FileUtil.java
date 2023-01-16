package com.curioud.ksmtest.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

  private String folderPath = "D:\\toy\\ksmtest\\src\\main\\resources\\static\\img\\";

  /*
    * mime type란 - 미디어 유형을 나타냄
    */
  // mime type 얻기
  public String getMimeType(
    MultipartFile file
  ) throws IOException {
    Tika tika = new Tika();
    InputStream isFile = file.getInputStream();
    String mimeType = tika.detect(isFile);
    isFile.close();
    return mimeType;
  }

  // 용량 검사
  public boolean isFileSize(long fileSize) {
    long limitFileSize = (1024 * 1024) * 10;  // 약 10MB

    if (fileSize > limitFileSize) {
      return false;
    }

    return true;
  }

  // mime type 검사
  public boolean isFileMimeType(String extension) {
    String[] extensions = {
      "image/jpg",
      "image/jpeg",
      "image/png",
      "image/bmp"
    };

    for (String item : extensions) {
      if (item.equalsIgnoreCase(extension)) {
        return true;
      }
    }

    return false;
  }

  // 파일이름 생성
  public String makeNewFilename() {
    Random random = new Random();
    StringBuilder filename = new StringBuilder();
    
    for (int i = 0; i < 9; i++) {
      filename.append(random.nextInt(10));
    }

    return filename.toString();
  }

  // 확장자 얻기
  public String getExtension(String mimeType) {
    // String extension = FilenameUtils.getExtension(mimeType);
    String extension = mimeType.split("/")[1];
    return extension;
  }

  // 파일 업로드
  public void uploadFile(
    MultipartFile file,
    String date,
    String newFilename
  ) throws IOException {
    String uploadFileLocation = "D:\\toy\\ksmtest\\src\\main\\resources\\static\\img\\" + date;
    String upload = uploadFileLocation + "\\" + newFilename;

    file.transferTo(new File(upload));
  }

  // 폴더 체크
  public boolean isDirectoryCheck(String date) {
    folderPath = "D:\\toy\\ksmtest\\src\\main\\resources\\static\\img\\" + date;
    File folder = new File(folderPath); 

    if (!folder.exists()) {
      return true;
    }
    return false;
  }

  // 폴더 생성
  public void makeDirectory(String date) {
    folderPath = "D:\\toy\\ksmtest\\src\\main\\resources\\static\\img\\" + date;
    File folder = new File(folderPath);

    folder.mkdir();
  }
}
