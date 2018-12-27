package com.zuoben.auth.feign;

import com.zuoben.util.resultUtils.JsonResult;
import com.zuoben.vo.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 远程调用用户服务
 */
@FeignClient(value = "zuoben-sys")
public interface IUserService {
    @PostMapping(value = "/login/validate")
    JsonResult<UserInfo> validate(@RequestParam("username") String username, @RequestParam("password") String password);
}
