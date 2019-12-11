package com.example.firstProject.config.imageValidate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@ApiModel(value = "ImageCode")
@Data
public class ImageCode {
  @ApiModelProperty(value = "图片")
  private BufferedImage image;

  @ApiModelProperty(value = "验证码")
  private String code;

  @ApiModelProperty(value = "过期时间")
  private LocalDateTime expireTime;

  public ImageCode(BufferedImage image, String code, int expireIn) {
    this.image = image;
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
    this.image = image;
    this.code = code;
    this.expireTime = expireTime;
  }

  @ApiOperation(value = "判断验证码是否已过期")
  boolean isExpire() {
    return LocalDateTime.now().isAfter(expireTime);
  }
}
