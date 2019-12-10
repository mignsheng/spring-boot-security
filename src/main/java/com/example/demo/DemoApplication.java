package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableConfigurationProperties({DataSourceConfig.class, BrowserSecurityConfig.class})
@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication dm = new SpringApplication();
    dm.run(DemoApplication.class, args);
  }
}
