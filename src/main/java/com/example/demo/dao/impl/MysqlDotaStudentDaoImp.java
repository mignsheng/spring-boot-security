package com.example.demo.dao.impl;

import com.example.demo.dao.MysqlDotaStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MysqlDotaStudentDaoImp implements MysqlDotaStudentDao {
  @Autowired
  @Qualifier("mysqlDotaJdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Map<String, Object>> getAllStudents() {
    return this.jdbcTemplate.queryForList("select * from student");
  }
}
