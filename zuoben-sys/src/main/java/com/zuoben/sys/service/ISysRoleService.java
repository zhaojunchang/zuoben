package com.zuoben.sys.service;

import com.zuoben.sys.dto.SysRoleDTO;
import com.zuoben.util.resultUtils.JsonResult;

/**
 * 角色Server
 * @author zuboen
 */
public interface ISysRoleService {
    /**
     * 分页获取列表
     * @param limit 每页条数
     * @param page 页数
     * @param name 角色名称
     * @return obj
     */
    JsonResult list(String name, Integer page, Integer limit);

    /**
     * 获取详情
     * @param id 主键
     * @return obj
     */
    JsonResult selectById(Integer id);

    /**
     * 新增
     * @param sysRoleDTO dto
     * @return obj
     */
    JsonResult insert(SysRoleDTO sysRoleDTO);

    /**
     * 更新
     * @param sysRoleDTO dto
     * @return obj
     */
    JsonResult update(SysRoleDTO sysRoleDTO);

    /**
     * 设置为失效
     * @param id 主键
     * @return obj
     */
    JsonResult disable(Integer id);

    /**
     * 删除
     * @param id 主键
     * @return obj
     */
    JsonResult delete(Integer id);

    /**
     * 批量删除s
     * @param ids 主键[id,id,id...]字符串
     * @return obj
     */
    JsonResult deletes(String ids);
}
