package com.example.firstProject.config.security;

import com.example.firstProject.bean.JwtUser;
import com.example.firstProject.bean.Student;
import com.example.firstProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
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
    System.out.println("================loadUserByUsername==============");
    // 模拟一个用户，替代数据库获取逻辑
    Student user = studentService.getStudentByName(username);
    if (user == null) {
      throw new UsernameNotFoundException(
          String.format("No user found with username '%s'.", username));
    }
    //    user.setPassword(this.passwordEncoder.encode("123456"));
    // 输出加密后的密码
    System.out.println(user.getPassword());
    // 权限
    List<GrantedAuthority> authorities = new ArrayList<>();
    //    if (StringUtils.equalsIgnoreCase("杜明", username)) {
    //      authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
    //    } else {
    //      authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("test");
    //    }
    user.setRoles("admin");
    return new JwtUser(user);
    //    return new User(
    //        username,
    //        user.getPassword(),
    //        user.isEnabled(),
    //        user.isAccountNonExpired(),
    //        user.isCredentialsNonExpired(),
    //        user.isAccountNonLocked(),
    //        authorities);
  }
}
