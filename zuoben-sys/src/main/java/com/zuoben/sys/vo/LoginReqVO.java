package com.zuoben.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class LoginReqVO {
    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;
}
