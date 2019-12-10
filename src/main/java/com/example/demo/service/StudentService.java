package com.example.demo.service;

import com.example.demo.Bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    int add(Student student);
    int update(Student student);
    int deleteBysno(String sno);
    List<Map<String, Object>> queryStudentListMap();
    Student queryStudentBySno(String sno);

    List<Map<String, Object>> getAllStudentsFromMysqlDota();
    List<Map<String, Object>> getAllStudentsFromMysqlTest();
}