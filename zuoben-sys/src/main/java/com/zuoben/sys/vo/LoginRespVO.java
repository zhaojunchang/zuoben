package com.zuoben.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class LoginRespVO {
    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("手机号")
    private String phonenum;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("认证类型")
    private Integer certificationType;

    @ApiModelProperty("apiKey")
    private String apiKey;

    @ApiModelProperty("apiSecret")
    private String apiSecret;
}
