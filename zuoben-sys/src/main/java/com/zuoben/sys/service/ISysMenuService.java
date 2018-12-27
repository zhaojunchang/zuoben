package com.zuoben.sys.service;

import com.zuoben.sys.vo.MenuTree;
import com.zuoben.util.resultUtils.JsonResult;

import java.util.List;

/**
 * @author zuoben
 */
public interface ISysMenuService {
    /**
     * 获取用户对应服务的菜单树
     *
     * @return obj
     */
    JsonResult<List<MenuTree>> selectUserMenu();

    /**
     * 获取所有菜单树
     *
     * @return obj
     */
    JsonResult<List<MenuTree>> list();

    /**
     * 获取菜单详情
     * @param id 主键
     * @return obj
     */
    JsonResult<MenuTree> selectById(Integer id);

    /**
     * 新增菜单
     * @param sysMenu obj
     * @return obj
     */
    JsonResult insert(MenuTree sysMenu);

    /**
     * 更新菜单
     * @param sysMenu obj
     * @return obj
     */
    JsonResult update(MenuTree sysMenu);

    /**
     * 把菜单设置为失效
     * @param id 主键
     * @return obj
     */
    JsonResult disableMenu(Integer id);

    /**
     * 删除菜单
     * @param id 主键
     * @return obj
     */
    JsonResult deleteMenu(Integer id);
}
