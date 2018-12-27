package com.zuoben.sys.controller;

import com.zuoben.sys.dto.SysUserDTO;
import com.zuoben.sys.service.ISysUserService;
import com.zuoben.util.resultUtils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统用户Controller
 * @author zuoben
 */
@RestController
@RequestMapping("sysUser")
@Api(value = "API - SysUserController", description = "系统用户")
public class SysUserController {

    @Resource
    private ISysUserService iSysUserService;

    @ApiOperation("管理列表")
    @GetMapping("list")
    public JsonResult list(@ApiParam("当前页") @RequestParam Integer page,
                           @ApiParam("每页条数") @RequestParam Integer limit,
                           @ApiParam("用户名") @RequestParam(required = false) String userName)
    {
        return iSysUserService.list(userName, page, limit);
    }

    @ApiOperation("获取详情")
    @GetMapping("select/{id}")
    public JsonResult selectById(@PathVariable Integer id) {
        return iSysUserService.selectById(id);
    }

    @ApiOperation("获取详情")
    @GetMapping("selectpro/{id}")
    public JsonResult selectproById(@PathVariable Integer id) {
        return iSysUserService.selectById(id);
    }

    @ApiOperation("获取系统详情")
    @GetMapping("select")
    public JsonResult selectById() {
        return iSysUserService.select();
    }

    @ApiOperation("新增")
    @PostMapping("insert")
    public JsonResult insert(@RequestBody SysUserDTO sysUserDTO) {
        return iSysUserService.insert(sysUserDTO);
    }

    @ApiOperation("更新")
    @PostMapping("update")
    public JsonResult update(@RequestBody SysUserDTO sysUserDTO) {
        return iSysUserService.update(sysUserDTO);
    }

    @ApiOperation("设置为不可用")
    @GetMapping("disable/{id}")
    public JsonResult disable(@PathVariable Integer id) {
        return iSysUserService.disable(id);
    }

    @ApiOperation("删除")
    @GetMapping("delete/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return iSysUserService.delete(id);
    }

    @ApiOperation("批量删除")
    @GetMapping("deletes/{ids}")
    public JsonResult deletes(@PathVariable String ids) {
        return iSysUserService.deletes(ids);
    }

    @ApiOperation("根据用户id获取用户状态")
    @PostMapping("state")
    public JsonResult getUserStateByUserId(Integer userId) {
        return iSysUserService.getUserStateByUserId(userId);
    }
}
