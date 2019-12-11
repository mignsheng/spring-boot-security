package com.example.firstProject.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(
    basePackages = MysqlSecondDatasourceConfig.PACKAGE,
    sqlSessionFactoryRef = "mysqlSecondSqlSessionFactory")
public class MysqlSecondDatasourceConfig {
  // dao扫描路径
  static final String PACKAGE = "com.example.firstProject.dao.mysqlSecondDao";
  // mybatis mapper扫描路径
  static final String MAPPER_LOCATION = "classpath:mapper/mysqlSecond/*.xml";

  @Bean(name = "mysqlseconddatasource")
  @ConfigurationProperties("spring.datasource.druid.mysqlSecond")
  public DataSource mysqlSecondDataSource() {
    return DruidDataSourceBuilder.create().build();
  }

  @Bean(name = "mysqlSecondTransactionManager")
  public DataSourceTransactionManager mysqlSecondTransactionManager() {
    return new DataSourceTransactionManager(mysqlSecondDataSource());
  }

  @Bean(name = "mysqlSecondSqlSessionFactory")
  public SqlSessionFactory mysqlSecondSqlSessionFactory(
      @Qualifier("mysqlseconddatasource") DataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    // 如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
    sessionFactory.setMapperLocations(
        new PathMatchingResourcePatternResolver()
            .getResources(MysqlSecondDatasourceConfig.MAPPER_LOCATION));
    return sessionFactory.getObject();
  }
}
