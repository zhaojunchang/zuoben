package com.zuoben.auth.service.impl;

import com.zuoben.auth.feign.IUserService;
import com.zuoben.auth.service.AuthService;
import com.zuoben.auth.util.user.JwtTokenUtil;
import com.zuoben.jwt.JWTInfo;
import com.zuoben.util.resultUtils.JsonResult;
import com.zuoben.vo.user.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private IUserService userService;

    @Override
    public JsonResult<String> login(String username, String password) {
        String token = "";
        JsonResult<UserInfo> jsonResult = userService.validate(username, password);
        UserInfo info = new UserInfo();
        if (jsonResult.isSuccess()) {
            info = jsonResult.getData();
        } else {
            return JsonResult.fail(jsonResult.getCode(), jsonResult.getMsg());
        }
        if (!StringUtils.isEmpty(info.getId())) {
            try {
                token = jwtTokenUtil.generateToken(new JWTInfo(info.getUsername(), info.getId(), info.getPhonenum()));
            } catch (Exception e) {
                return JsonResult.fail("获取用户信息异常");
            }
            return JsonResult.success(token);
        } else {
            return JsonResult.fail("获取用户信息失败");
        }
    }
}
