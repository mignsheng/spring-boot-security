package com.example.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@RestController
public class CookieController {
  private final Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping("/test/cookie")
  public String cookie(
      HttpServletRequest request, HttpServletResponse response, HttpSession session) {

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      Arrays.stream(cookies)
          .forEach(
              (cookie) ->
                  System.out.println("====" + cookie.getName() + " : " + cookie.getValue()));
    }
    response.addCookie(new Cookie("key", "value"));
    return "index";
  }

  @RequestMapping("/test/cookie1")
  public String cookie1(HttpServletRequest request) {
    HttpSession session = request.getSession(false);

    return "index";
  }
}
