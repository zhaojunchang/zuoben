package com.zuoben.auth.controller;

import com.zuoben.auth.configuration.KeyConfiguration;
import com.zuoben.auth.service.AuthService;
import com.zuoben.auth.util.user.JwtAuthenticationRequest;
import com.zuoben.auth.util.user.JwtTokenUtil;
import com.zuoben.jwt.IJWTInfo;
import com.zuoben.util.resultUtils.JsonResult;
import com.zuoben.vo.user.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * token相关
 */
@Api(value = "API - AuthController", description = "用户token相关")
@RestController
@RequestMapping("jwt")
public class AuthController {

    @Autowired
    private KeyConfiguration keyConfiguration;
    @Autowired
    private AuthService authService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "用户登录,返回token", notes = "用户登录,返回token")
    @RequestMapping(value = "token", method = RequestMethod.POST)
    public JsonResult createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        return authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    }

    @ApiOperation(value = "获取token用户信息", notes = "获取token用户信息")
    @RequestMapping(value = "getInfoFromToken", method = RequestMethod.GET)
    public UserInfo getInfoFromToken(@ApiParam(required = true, value = "用户token") @RequestParam String token) throws Exception {
        UserInfo userInfo = new UserInfo();
        IJWTInfo ijwtInfo = jwtTokenUtil.getInfoFromToken(token);
        userInfo.setId(ijwtInfo.getId());
        userInfo.setUsername(ijwtInfo.getUserName());
        userInfo.setCId(ijwtInfo.getCId());
        userInfo.setPhonenum(ijwtInfo.getPhone());
        return userInfo;
    }

//    @ApiOperation(value = "校验token", notes = "校验token")
//    @RequestMapping(value = "verify", method = RequestMethod.GET)
//    public JsonResult verify(@ApiParam(required = true, value = "用户token") @RequestParam String token) throws Exception {
//        authService.validate(token);
//        return JsonResult.success();
//    }

    /**
     * 获取用户token公钥
     *
     * @return
     */
    @ApiOperation(value = "获取用户token公钥,供远程服务解密使用,无需手动调用", notes = "获取用户token公钥,供远程服务解密使用,无需手动调用")
    @RequestMapping(value = "/userPubKey", method = RequestMethod.POST)
    public JsonResult<byte[]> getUserPublicKey() {
        return JsonResult.success(keyConfiguration.getUserPubKey());
    }
}
