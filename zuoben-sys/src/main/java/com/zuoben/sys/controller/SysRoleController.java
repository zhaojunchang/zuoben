package com.zuoben.sys.controller;

import com.zuoben.sys.dto.SysRoleDTO;
import com.zuoben.sys.service.ISysRoleService;
import com.zuoben.util.resultUtils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zuoben
 */
@RestController
@RequestMapping("sysRole")
@Api(value = "API - SysRoleController", description = "系统角色")
public class SysRoleController {

    @Resource
    private ISysRoleService iSysRoleService;

    @ApiOperation("管理列表")
    @GetMapping("list")
    public JsonResult list(@ApiParam("当前页") @RequestParam Integer page,
                           @ApiParam("每页条数") @RequestParam Integer limit,
                           @ApiParam("角色名") @RequestParam(required = false) String name)
    {
        return iSysRoleService.list(name, page, limit);
    }

    @ApiOperation("获取详情")
    @GetMapping("select/{id}")
    public JsonResult selectById(@PathVariable Integer id) {
        return iSysRoleService.selectById(id);
    }

    @ApiOperation("新增")
    @PostMapping("insert")
    public JsonResult insert(@RequestBody SysRoleDTO sysRoleDTO) {
        return iSysRoleService.insert(sysRoleDTO);
    }

    @ApiOperation("更新")
    @PostMapping("update")
    public JsonResult update(@RequestBody SysRoleDTO sysRoleDTO) {
        return iSysRoleService.update(sysRoleDTO);
    }

    @ApiOperation("设置为不可用")
    @GetMapping("disable/{id}")
    public JsonResult disable(@PathVariable Integer id) {
        return iSysRoleService.disable(id);
    }

    @ApiOperation("删除")
    @GetMapping("delete/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return iSysRoleService.delete(id);
    }

    @ApiOperation("批量删除")
    @GetMapping("deletes/{ids}")
    public JsonResult deletes(@PathVariable String ids) {
        return iSysRoleService.deletes(ids);
    }
}
