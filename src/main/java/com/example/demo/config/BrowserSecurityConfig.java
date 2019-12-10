package com.example.demo.config;

import com.example.demo.Filter.SmsCodeFilter;
import com.example.demo.Validate.ImageCode.ValidateCodeFilter;
import com.example.demo.Validate.Session.MySessionExpiredStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired private MyLogOutSuccessHandler myLogOutSuccessHandler;

  @Autowired private MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler;

  @Autowired private MyUserDetailService userDetailService;

  @Autowired private DataSource dataSource;

  @Autowired private MyAuthenticationSucessHandler authenticationSucessHandler;

  @Autowired private MyAuthenticationFailureHandler authenticationFailureHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired private SmsCodeFilter smsCodeFilter;

  @Autowired private SmsAuthenticationConfig smsAuthenticationConfig;

  @Autowired private ValidateCodeFilter validateCodeFilter;

  @Autowired private MySessionExpiredStrategy mySessionExpiredStrategy;

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
    jdbcTokenRepository.setDataSource(dataSource);
    jdbcTokenRepository.setCreateTableOnStartup(false);
    return jdbcTokenRepository;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.exceptionHandling()
        .accessDeniedHandler(myAuthenticationAccessDeniedHandler)
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
        .antMatchers(
            "/authentication/require",
            "/login.html",
            "/code/image",
            "/code/sms",
            "/session/invalid")
        .permitAll() // 登录跳转 URL 无需认证
        .anyRequest() // 所有请求
        .authenticated() // 都需要认证
        //        .and()
        //        .sessionManagement() // 添加 Session管理器
        //        .invalidSessionUrl("/session/invalid") // Session失效后跳转到这个链接
        //        .maximumSessions(1)
        //        .maxSessionsPreventsLogin(true)
        //        .expiredSessionStrategy(mySessionExpiredStrategy)
        //        .and()
        .and()
        .logout()
        .logoutUrl("/signout")
        //                 .logoutSuccessUrl("/signout/success")
        .logoutSuccessHandler(myLogOutSuccessHandler)
        .deleteCookies("JSESSIONID")
        .and()
        .csrf()
        .disable()
        .apply(smsAuthenticationConfig); // 将短信验证码认证配置加到 Spring Security 中
  }
}
