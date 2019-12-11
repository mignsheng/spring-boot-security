package com.example.firstProject.dao.mysqlSecondDao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface MysqlSecondStudentMapper {
  //  @Insert("insert into student(no,name,sex) values(#{no},#{name},#{sex})")
  //  int add(Student student);
  //
  //  @Update("update student set name=#{name},sex=#{sex} where no=#{no}")
  //  int update(Student student);
  //
  //  @Delete("delete from student where no=#{no}")
  //  int deleteByNo(String no);
  //
  //  @Select("select * from student where no=#{no}")
  //  @Results(
  //      id = "student",
  //      value = {
  //        @Result(property = "no", column = "no", javaType = String.class),
  //        @Result(property = "name", column = "sname", javaType = String.class),
  //        @Result(property = "sex", column = "ssex", javaType = String.class)
  //      })
  //  Student queryStudentByNo(String no);

  List<Map<String, Object>> getStudentOnlyOne();
}
