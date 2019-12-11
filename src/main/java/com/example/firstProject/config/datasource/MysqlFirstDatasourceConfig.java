package com.example.firstProject.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(
    basePackages = MysqlFirstDatasourceConfig.PACKAGE,
    sqlSessionFactoryRef = "mysqlFirstSqlSessionFactory")
public class MysqlFirstDatasourceConfig {
  // dao扫描路径
  static final String PACKAGE = "com.example.firstProject.dao.mysqlFirstDao";
  // mybatis mapper扫描路径
  static final String MAPPER_LOCATION = "classpath:mapper/mysqlFirst/*.xml";

  @Primary
  @Bean(name = "mysqlfirstdatasource")
  @ConfigurationProperties("spring.datasource.druid.mysqlFirst")
  public DataSource mysqlFirstDataSource() {
    return DruidDataSourceBuilder.create().build();
  }

  @Bean(name = "mysqlFirstTransactionManager")
  @Primary
  public DataSourceTransactionManager mysqlFirstTransactionManager() {
    return new DataSourceTransactionManager(mysqlFirstDataSource());
  }

  @Bean(name = "mysqlFirstSqlSessionFactory")
  @Primary
  public SqlSessionFactory mysqlFirstSqlSessionFactory(
      @Qualifier("mysqlfirstdatasource") DataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    // 如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
    sessionFactory.setMapperLocations(
        new PathMatchingResourcePatternResolver()
            .getResources(MysqlFirstDatasourceConfig.MAPPER_LOCATION));
    return sessionFactory.getObject();
  }
}
