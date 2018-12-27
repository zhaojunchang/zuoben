package com.zuoben.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统角色dto
 * @author zuoben
 */
@Data
@ApiModel
public class SysRoleDTO implements Serializable {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("角色名")
    private String name;

    @ApiModelProperty("0正常  1禁用  2删除")
    private Integer state;

    @ApiModelProperty("菜单数组")
    private List<Integer> menuList;

    private static final long serialVersionUID = 1L;
}