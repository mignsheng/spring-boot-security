package com.example.demo.Filter;

import com.example.demo.Bean.SmsCode;
import com.example.demo.Controller.ValidateController;
import com.example.demo.Validate.ImageCode.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SmsCodeFilter extends OncePerRequestFilter {

  @Autowired private AuthenticationFailureHandler authenticationFailureHandler;

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {
    if (StringUtils.equalsIgnoreCase("/login/mobile", httpServletRequest.getRequestURI())
        && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {
      try {
        validateCode(new ServletWebRequest(httpServletRequest));
      } catch (ValidateCodeException e) {
        authenticationFailureHandler.onAuthenticationFailure(
            httpServletRequest, httpServletResponse, e);
        return;
      }
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  private void validateCode(ServletWebRequest servletWebRequest)
      throws ServletRequestBindingException {
    String smsCodeInRequest =
        ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "smsCode");
    String mobile =
        ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "mobile");
    SmsCode codeInSession =
        (SmsCode)
            sessionStrategy.getAttribute(
                servletWebRequest, ValidateController.SESSION_KEY_SMS_CODE + mobile);

    if (StringUtils.isBlank(smsCodeInRequest)) {
      throw new ValidateCodeException("验证码不能为空！");
    }
    if (codeInSession == null) {
      throw new ValidateCodeException("验证码不存在，请重新发送！");
    }
    if (codeInSession.isExpire()) {
      sessionStrategy.removeAttribute(
          servletWebRequest, ValidateController.SESSION_KEY_SMS_CODE + mobile);
      throw new ValidateCodeException("验证码已过期，请重新发送！");
    }
    if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), smsCodeInRequest)) {
      throw new ValidateCodeException("验证码不正确！");
    }
    sessionStrategy.removeAttribute(
        servletWebRequest, ValidateController.SESSION_KEY_SMS_CODE + mobile);
  }
}
