package com.zuoben.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息dto
 * @author zuoben
 */
@Data
@ApiModel
public class ZuoConsumerDTO implements Serializable {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String phonenum;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("用户名")
    private String loginname;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("公司")
    private String company;

    @ApiModelProperty("头像")
    private String headimgurl;

    @ApiModelProperty("0正常  1禁用  2删除")
    private Integer state;

    @ApiModelProperty("备注")
    private String remark;

    private static final long serialVersionUID = 1L;
}