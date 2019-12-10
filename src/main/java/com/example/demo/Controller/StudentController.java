package com.example.demo.Controller;

import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("querystudentsfromDota")
    public List<Map<String, Object>> queryStudentsFromDota(){
        return this.studentService.getAllStudentsFromMysqlDota();
    }

    @RequestMapping("querystudentsfromTest")
    public List<Map<String, Object>> queryStudentsFromTest(){
        return this.studentService.getAllStudentsFromMysqlTest();
    }
}