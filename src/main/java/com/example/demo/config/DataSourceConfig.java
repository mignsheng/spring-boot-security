package com.example.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

  @Primary
  @Bean(name = "mysqlTest")
  @ConfigurationProperties("spring.datasource.druid.mysqlTest")
  public DataSource dataSourceOne() {
    return DruidDataSourceBuilder.create().build();
  }

  @Bean(name = "mysqlDota")
  @ConfigurationProperties("spring.datasource.druid.mysqlDota")
  public DataSource dataSourceTwo() {
    return DruidDataSourceBuilder.create().build();
  }

  @Bean(name = "mysqlTestTemplate")
  public JdbcTemplate primaryJdbcTemplate(@Qualifier("mysqlTest") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean(name = "mysqlDotaJdbcTemplate")
  public JdbcTemplate secondaryJdbcTemplate(@Qualifier("mysqlDota") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
