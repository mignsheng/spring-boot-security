package com.example.firstProject.controller;

import com.example.firstProject.bean.Student;
import com.example.firstProject.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

  @Autowired private StudentService studentService;

  //  @ApiIgnore
  //  @ApiImplicitParam(name = "user", value = "用户实体", required = false, dataType = "User")
  @ApiOperation(value = "首页", notes = "首页")
  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public List<Student> Login() {
    List<Student> list = new ArrayList<>();
    list.addAll(this.studentService.getStudentOnlyOne());
    list.addAll(this.studentService.getAllStudents());
    return list;
  }

  @GetMapping("/auth/admin")
  @PreAuthorize("hasAuthority('admin')")
  public String authenticationTest() {
    return "您拥有admin权限，可以查看";
  }
}
