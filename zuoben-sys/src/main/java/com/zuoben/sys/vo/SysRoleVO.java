package com.zuoben.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel
@Data
public class SysRoleVO {
    private Integer id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色状态")
    private Integer state;

    @ApiModelProperty("菜单数组")
    private List<Integer> menuList;

    @ApiModelProperty("创建时间")
    private Date createTime;
}