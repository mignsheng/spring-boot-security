package com.example.demo.Bean;

import java.io.Serializable;
import java.util.Date;

public class MyUser implements Serializable {
  private static final long serialVersionUID = 3497935890426858541L;

  private String userName;

  private String password;

  private Date add_date;

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  private int age;

  private boolean accountNonExpired = true;

  private boolean accountNonLocked = true;

  private boolean credentialsNonExpired = true;

  private boolean enabled = true;

  public Date getAdd_date() {
    return add_date;
  }

  public void setAdd_date(Date add_date) {
    this.add_date = add_date;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
