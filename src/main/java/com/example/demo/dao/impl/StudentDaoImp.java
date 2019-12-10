package com.example.demo.dao.impl;

import com.example.demo.Bean.Student;
import com.example.demo.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository("studentDao")
public class StudentDaoImp implements StudentDao {

  @Autowired
  @Qualifier("mysqlTestTemplate")
  private JdbcTemplate jdbcTemplate;

  @Override
  public int add(Student student) {
    //         String sql = "insert into student(no,name,sex) values(?,?,?)";
    //         Object[] args = { student.getNo(), student.getName(), student.getSex() };
    //         int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
    //         return this.jdbcTemplate.update(sql, args, argTypes);
    String sql = "insert into student(no,name,sex) values(:no,:name,:sex)";
    NamedParameterJdbcTemplate npjt =
        new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
    return npjt.update(sql, new BeanPropertySqlParameterSource(student));
  }

  @Override
  public int update(Student student) {
    String sql = "update student set name = ?,sex = ? where no = ?";
    Object[] args = {student.getName(), student.getSex(), student.getNo()};
    int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
    return this.jdbcTemplate.update(sql, args, argTypes);
  }

  @Override
  public int deleteBysno(String no) {
    String sql = "delete from student where no = ?";
    Object[] args = {no};
    int[] argTypes = {Types.VARCHAR};
    return this.jdbcTemplate.update(sql, args, argTypes);
  }

  @Override
  public List<Map<String, Object>> queryStudentsListMap() {
    String sql = "select * from student";
    return this.jdbcTemplate.queryForList(sql);
  }

  public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
      Student student = new Student();
      student.setNo(rs.getString("no"));
      student.setName(rs.getString("name"));
      student.setSex(rs.getString("sex"));
      return student;
    }
  }

  @Override
  public Student queryStudentBySno(String no) {
    String sql = "select * from student where no = ?";
    Object[] args = {no};
    int[] argTypes = {Types.VARCHAR};
    List<Student> studentList = this.jdbcTemplate.query(sql, args, argTypes, new StudentMapper());
    if (studentList != null && studentList.size() > 0) {
      return studentList.get(0);
    } else {
      return null;
    }
  }
}
