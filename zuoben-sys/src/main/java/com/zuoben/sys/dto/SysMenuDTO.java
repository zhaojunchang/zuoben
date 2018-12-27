package com.zuoben.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统菜单dto
 * @author zuoben
 */
@Data
@ApiModel
public class SysMenuDTO implements Serializable {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("名称")
    private String text;

    @ApiModelProperty("路径")
    private String href;

    @ApiModelProperty("父节点id")
    private Integer parentId;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("0正常  1禁用  2删除")
    private Integer state;

    private static final long serialVersionUID = 1L;
}