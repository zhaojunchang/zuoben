package com.zuoben.sys.controller;

import com.zuoben.sys.service.ISysMenuService;
import com.zuoben.sys.vo.MenuTree;
import com.zuoben.util.resultUtils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zuoben
 */
@RestController
@RequestMapping("sysMenu")
@Api(value = "API - SysMenuController", description = "系统菜单")
public class SysMenuController {

    @Resource
    private ISysMenuService iSysMenuService;

    @ApiOperation("进入系统菜单管理首页")
    @GetMapping("list")
    public JsonResult list() {
        return iSysMenuService.list();
    }

    @ApiOperation("获取菜单详情")
    @GetMapping("select/{id}")
    public JsonResult selectById(@PathVariable Integer id) {
        return iSysMenuService.selectById(id);
    }

    @ApiOperation("查询系统用户菜单")
    @GetMapping("current")
    public JsonResult selectUserSideMenu() {
        return iSysMenuService.selectUserMenu();
    }

    @ApiOperation("新增菜单")
    @PostMapping("insert")
    public JsonResult insert(@RequestBody MenuTree sysMenu) {
        return iSysMenuService.insert(sysMenu);
    }

    @ApiOperation("更新菜单")
    @PostMapping("update")
    public JsonResult update(@RequestBody MenuTree sysMenu) {
        return iSysMenuService.update(sysMenu);
    }

    @ApiOperation("设置为不可用")
    @GetMapping("disable/{id}")
    public JsonResult disable(@PathVariable Integer id) {
        return iSysMenuService.disableMenu(id);
    }

    @ApiOperation("删除菜单")
    @GetMapping("delete/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return iSysMenuService.deleteMenu(id);
    }
}
