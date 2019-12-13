package com.example.firstProject.config.security;

import com.example.firstProject.bean.Student;
import com.example.firstProject.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserDetailService implements UserDetailsService {

  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private StudentService studentService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 查询用户信息
    //    Student user = studentService.getStudentByName(username);
    //    if (user == null) {
    //      System.out.println("用户不存在！");
    //      throw new UsernameNotFoundException(
    //          String.format("No user found with username '%s'.", username));
    //    }

    // 模拟一个用户，替代数据库获取逻辑
    Student user = new Student();
    user.setName(username);
    user.setPassword(this.passwordEncoder.encode("123456"));

    // 输出加密后的密码
    System.out.println(user.getPassword());
    // 权限
    List<GrantedAuthority> authorities = new ArrayList<>();
    if (StringUtils.equalsIgnoreCase("杜明", username)) {
      authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
    } else {
      authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("test");
    }

    return new User(
        username,
        user.getPassword(),
        user.isEnabled(),
        user.isAccountNonExpired(),
        user.isCredentialsNonExpired(),
        user.isAccountNonLocked(),
        authorities);
  }
}
