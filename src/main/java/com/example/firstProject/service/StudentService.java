package com.example.firstProject.service;

import com.example.firstProject.bean.Student;

import java.util.List;

public interface StudentService {
  //  int add(Student student);
  //
  //  int update(Student student);
  //
  //  int deleteByNo(String no);
  //
  //  Student queryStudentByNo(String no);

  List<Student> getAllStudents();

  List<Student> getStudentOnlyOne();

  Student getStudentByName(Student student);
}
