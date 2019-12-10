package com.example.demo.dao;

import com.example.demo.Bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    int add(Student student);

    int update(Student student);
    int deleteBysno(String sno);
    List<Map<String, Object>> queryStudentsListMap();
    Student queryStudentBySno(String sno);
}
