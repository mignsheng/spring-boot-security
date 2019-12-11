package com.example.firstProject.config.SmsCodeValidate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel(value = "SmsCode")
@Data
public class SmsCode {
  @ApiModelProperty(value = "短信验证码")
  private String code;

  @ApiModelProperty(value = "过期时间")
  private LocalDateTime expireTime;

  public SmsCode(String code, int expireIn) {
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  public SmsCode(String code, LocalDateTime expireTime) {
    this.code = code;
    this.expireTime = expireTime;
  }

  @ApiModelProperty(value = "是否过期")
  boolean isExpire() {
    return LocalDateTime.now().isAfter(expireTime);
  }
}
