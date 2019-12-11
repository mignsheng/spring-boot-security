package com.example.firstProject.config.SmsCodeValidate;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SmsCodeValidateController {

  public static final String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  @GetMapping("/code/sms")
  public void createSmsCode(HttpServletRequest request, HttpServletResponse response, String mobile)
      throws IOException {
    SmsCode smsCode = createSMSCode();
    sessionStrategy.setAttribute(
        new ServletWebRequest(request), SESSION_KEY_SMS_CODE + mobile, smsCode);
    // 输出验证码到控制台代替短信发送服务
    System.out.println("您的登录验证码为：" + smsCode.getCode() + "，有效时间为60秒");
  }

  private SmsCode createSMSCode() {
    String code = RandomStringUtils.randomNumeric(6);
    return new SmsCode(code, 60);
  }
}
