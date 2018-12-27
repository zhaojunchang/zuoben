package com.zuoben.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 系统用户dto
 * @author zuoben
 */
@Data
@ApiModel
public class SysUserDTO implements Serializable {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像")
    private String headimgurl;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("0正常  1禁用  2删除")
    private Integer state;

    @ApiModelProperty("用户角色id")
    private List<Integer> roleList;

    private static final long serialVersionUID = 1L;
}