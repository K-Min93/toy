package com.curioud.ksmtest.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExcptionController {
  
  private Logger logger = LoggerFactory.getLogger(getClass());

  /*
   * 에러에 상응하는 상태 코드값 알려주기
   * 300대 : 리다이렉션 
   * 400대 : 클라이언트 오류
   * 500대 : 서버 오류
   */

  /*
    404 error 
    - http status code
    - error에 대한 메세지
  */ 
  @ExceptionHandler({NoHandlerFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String notFoundExcption(
    NoHandlerFoundException ex
  ) {
    logger.info("===== 404 Not Found Excption =====");

    return "error/error";
  }

  /*
   * 데이터 조회 x
   * - http status code
   * - error에 대한 메세지
   */
  @ExceptionHandler({NullPointerException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String nullPointExcption(
    NullPointerException ex
  ) {

    return "error/error";
  }

  @ExceptionHandler({Exception.class})
  public String handle(
    Exception ex
  ) {

    return "error/error";
  }
}
