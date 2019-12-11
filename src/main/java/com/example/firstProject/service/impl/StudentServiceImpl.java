package com.example.firstProject.service.impl;

import com.example.firstProject.bean.Student;
import com.example.firstProject.dao.mysqlFirstDao.MysqlFirstStudentMapper;
import com.example.firstProject.dao.mysqlSecondDao.MysqlSecondStudentMapper;
import com.example.firstProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {

  @Autowired private MysqlFirstStudentMapper mysqlFirstStudentMapper;

  @Autowired private MysqlSecondStudentMapper mysqlSecondStudentMapper;

  //  @Override
  //  public int add(Student student) {
  //    return this.studentMapper.add(student);
  //  }
  //
  //  @Override
  //  public int update(Student student) {
  //    return this.studentMapper.update(student);
  //  }
  //
  //  @Override
  //  public int deleteByNo(String sno) {
  //    return this.studentMapper.deleteByNo(sno);
  //  }
  //
  //  @Override
  //  public Student queryStudentByNo(String sno) {
  //    return this.studentMapper.queryStudentByNo(sno);
  //  }

  @Override
  public List<Map<String, Object>> getAllStudents() {
    return this.mysqlFirstStudentMapper.getAllStudents();
  }

  @Override
  public List<Map<String, Object>> getStudentOnlyOne() {
    return this.mysqlSecondStudentMapper.getStudentOnlyOne();
  }

  @Override
  public Student getStudentByName(String name) {
    return this.mysqlFirstStudentMapper.getStudentByName(name);
  }
}
