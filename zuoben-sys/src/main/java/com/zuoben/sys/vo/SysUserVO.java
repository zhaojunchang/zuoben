package com.zuoben.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author
 * createTime:2018/3/29 0029
 * 用户信息vo
 */
@ApiModel
@Data
public class SysUserVO {
    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("0正常  1禁用  2删除")
    private Integer state;

    @ApiModelProperty("用户角色id")
    private Integer roleId;

    @ApiModelProperty("用户角色名称")
    private String roleName;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String headimgurl;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
