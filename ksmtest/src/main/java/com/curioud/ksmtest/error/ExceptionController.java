package com.curioud.ksmtest.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController implements ErrorController {
  
  private Logger logger = LoggerFactory.getLogger(getClass());

  /*
   * 에러에 상응하는 상태 코드값 알려주기
   * 300대 : 리다이렉션 
   * 400대 : 클라이언트 오류
   * 500대 : 서버 오류
   */

  /*
   * - http status code로 상태에 맞게 처리
   * 
   */
  @ExceptionHandler({Exception.class})
  public String exceptionHandler(
    Exception ex,
    HttpServletRequest request,
    Model model
  ) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    int statusCode = 0;

    if (status != null) {
      statusCode = Integer.valueOf(status.toString());
      System.out.println("http status code toString : ===== " + status.toString() + " =====");
    }
    
    System.out.println("http status code int : ===== " + statusCode + " =====");

    model.addAttribute("statusCode", statusCode);
    return "error/error";
  }
}
