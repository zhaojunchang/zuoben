package com.zuoben.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class RegisterReqVO {
    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phoneNum;

    @ApiModelProperty("验证码")
    private String verifyCode;

    @ApiModelProperty("登录名")
    private String userNameEmailPhone;
}
