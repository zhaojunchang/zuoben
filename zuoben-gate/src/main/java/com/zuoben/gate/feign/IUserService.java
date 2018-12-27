package com.zuoben.gate.feign;

import com.zuoben.util.resultUtils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 用户权限相关
 */
@FeignClient(value = "zuoben-sys")
public interface IUserService {

    /**
     * 根据用户id获取用户状态
     */
    @PostMapping(value = "/sysUser/state")
    JsonResult getUserStateByUserId(@RequestParam("userId") Integer userId);
}
