package com.zuoben.sys.vo;


import com.zuoben.vo.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Ace on 2017/6/12.
 */

@ApiModel
@Data
public class MenuTree extends TreeNode {
    @ApiModelProperty("菜单图标")
    String icon;
    @ApiModelProperty("菜单名称")
    String text;
    @ApiModelProperty("url路径")
    String href;
    @ApiModelProperty("菜单是否展开")
    State state = new State();
    @ApiModelProperty("菜单状态")
    Integer situation;
    @ApiModelProperty("菜单排序")
    Integer sort;
    @ApiModelProperty("菜单类型")
    Integer type;
    @ApiModelProperty("菜单上下级关系")
    String path;

    public MenuTree() {
    }

    public MenuTree(int id, String text, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.text = text;
    }

    public MenuTree(int id, String text, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.text = text;
    }
}

class State {
    // 菜单是否展开
    boolean opened = true;

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
}