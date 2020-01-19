package com.example.firstProject.service;

import com.example.firstProject.bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
  //  int add(Student student);
  //
  //  int update(Student student);
  //
  //  int deleteByNo(String no);
  //
  //  Student queryStudentByNo(String no);

  List<Map<String, Object>> getAllStudents();

  List<Map<String, Object>> getStudentOnlyOne();

  Student getStudentByName(String name);
}
