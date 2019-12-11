package com.example.firstProject.config.security;

import com.example.firstProject.config.SmsCodeValidate.SmsAuthenticationConfig;
import com.example.firstProject.config.SmsCodeValidate.SmsCodeFilter;
import com.example.firstProject.config.imageValidate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired MyAuthenticationSucessHandler authenticationSucessHandler;

  @Autowired MyAuthenticationFailureHandler authenticationFailureHandler;

  @Autowired private ValidateCodeFilter validateCodeFilter;

  @Autowired private UserDetailService userDetailService;

  @Autowired private DataSource dataSource;

  @Autowired private SmsCodeFilter smsCodeFilter;

  @Autowired private SmsAuthenticationConfig smsAuthenticationConfig;

  @Autowired private MyAuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.exceptionHandling()
        .accessDeniedHandler(authenticationAccessDeniedHandler)
        .and()
        .addFilterBefore(
            validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器
        .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加短信验证码校验过滤器
        .formLogin() // 表单登录
        // http.httpBasic() // HTTP Basic
        .loginPage("/authentication/require") // 登录跳转 URL
        .loginProcessingUrl("/login") // 处理表单登录 URL
        .successHandler(authenticationSucessHandler) // 处理登录成功
        .failureHandler(authenticationFailureHandler) // 处理登录失败
        .and()
        .rememberMe()
        .tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
        .tokenValiditySeconds(3600) // remember 过期时间，单为秒
        .userDetailsService(userDetailService) // 处理自动登录逻辑
        .and()
        .authorizeRequests() // 授权配置
        .antMatchers("/authentication/require", "/login.html", "/code/image", "/code/sms")
        .permitAll() // 无需认证的请求路径
        .anyRequest() // 所有请求
        .authenticated() // 都需要认证
        .and()
        .csrf()
        .disable();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
    jdbcTokenRepository.setDataSource(dataSource);
    jdbcTokenRepository.setCreateTableOnStartup(false);
    return jdbcTokenRepository;
  }
}
