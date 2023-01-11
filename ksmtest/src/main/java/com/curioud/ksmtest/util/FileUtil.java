package com.curioud.ksmtest.util;

import java.util.Random;

public class FileUtil {
  
  // 확장자 분리
  public String getExtension(String originalFilename) {
    return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
  }

  // 용량 검사
  public boolean validateFileSize(long fileSize) {
    long limitFileSize = (1024 * 1024) * 10;  // 약 10MB

    if (fileSize > limitFileSize) {
      return false;
    }

    return true;
  }
  
  // 확장자 검사
  public boolean validateFileExtension(String extension) {
    String[] extensions = {"jpg", "jpeg", "png", "bmp"};
    
    for (String item : extensions) {
      if (item.equalsIgnoreCase(extension)) {
        return true;
      }
    }

    return false;
  }

  public String makeNewFilename(String extension) {
    Random random = new Random();
    StringBuilder filename = new StringBuilder();
    
    for (int i = 0; i < 9; i++) {
      filename.append(random.nextInt(10));
    }

    // 확장자 추가
    filename.append("." + extension);

    return filename.toString();
  }
}
