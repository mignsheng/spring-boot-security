package com.example.firstProject.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "Student")
@Data
public class Student {
  @ApiModelProperty(value = "主id")
  private int id;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "性别")
  private int sex;

  @ApiModelProperty(value = "年龄")
  private int age;

  @ApiModelProperty(value = "密码")
  private String password;

  @ApiModelProperty(value = "编号")
  private String no;

  @ApiModelProperty(value = "添加日期")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date add_date;

  @ApiModelProperty(value = "判断账户是否未过期，未过期true反之false")
  private boolean accountNonExpired = true;

  @ApiModelProperty(value = "判断账户是否未锁定")
  private boolean accountNonLocked = true;

  @ApiModelProperty(value = "判断用户凭证是否没过期，即密码是否未过期")
  private boolean credentialsNonExpired = true;

  @ApiModelProperty(value = "判断用户是否可用")
  private boolean enabled = true;
}
