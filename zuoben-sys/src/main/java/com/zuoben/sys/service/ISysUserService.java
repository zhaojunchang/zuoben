package com.zuoben.sys.service;

import com.zuoben.sys.dto.SysUserDTO;
import com.zuoben.util.resultUtils.JsonResult;


/**
 * 系统用户Server
 * @author zuoben
 */
public interface ISysUserService {

    /**
     * 用户登录
     * @param username 系统登录名
     * @param password 系统登录密码
     * @return JsonResult
     */
    JsonResult validate(String username, String password);

    /**
     * 分页获取所有
     * @param page 当前页
     * @param limit 每页显示的数量
     * @param userName 查询条件
     * @return JsonResult
     */
    JsonResult list(String userName, Integer page, Integer limit);

    /**
     * 获取用户详情
     * @param id 主键
     * @return JsonResult
     */
    JsonResult selectById(Integer id);

    /**
     * 获取系统详情
     * @return JsonResult
     */
    JsonResult select();

    /**
     * 新增
     * @param sysUserDTO dto
     * @return JsonResult
     */
    JsonResult insert(SysUserDTO sysUserDTO);

    /**
     * 更新
     * @param sysUserDTO dto
     * @return JsonResult
     */
    JsonResult update(SysUserDTO sysUserDTO);

    /**
     * 设置为失效
     * @param id 主键
     * @return JsonResult
     */
    JsonResult disable(Integer id);

    /**
     * 删除
     * @param id 主键
     * @return JsonResult
     */
    JsonResult delete(Integer id);

    /**
     * 批量删除
     * @param ids 主键[id,id,id...]字符串
     * @return JsonResult
     */
    JsonResult deletes(String ids);

    /**
     * 根据用户id获取用户状态
     * @param userId 用户id
     * @return JsonResult
     */
    JsonResult getUserStateByUserId(Integer userId);
}
