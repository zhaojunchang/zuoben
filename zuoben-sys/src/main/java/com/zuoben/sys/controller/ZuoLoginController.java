package com.zuoben.sys.controller;

import com.zuoben.sys.service.ISysUserService;
import com.zuoben.sys.service.ZuoConsumerService;
import com.zuoben.sys.vo.LoginRespVO;
import com.zuoben.sys.vo.RegisterReqVO;
import com.zuoben.util.resultUtils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zuoben
 */
@RestController
@RequestMapping("login")
@Api(value = "API - ZuoLoginController", description = "用户登录相关")
public class ZuoLoginController {
    @Resource
    private ZuoConsumerService zuoConsumerService;
    @Resource
    private ISysUserService iSysUserService;

    @ApiOperation("发送验证码")
    @GetMapping("sendVerifyCode")
    public JsonResult sendVerifyCode(String userNameEmailPhone) {
        return zuoConsumerService.sendVerifyCode(userNameEmailPhone);
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public JsonResult<LoginRespVO> register(RegisterReqVO reqVO) {
        return zuoConsumerService.register(reqVO);
    }

    @ApiOperation("后台用户登录")
    @PostMapping("validate")
    public JsonResult validate(String username, String password) {
        return iSysUserService.validate(username, password);
    }
    /**
     * 校验验证码是否正确
     */
    @ApiOperation("校验验证码是否正确")
    @PostMapping("checkVerifyCode")
    public JsonResult checkVerifyCode(String userNameEmailPhone, String code) {
        return zuoConsumerService.checkVerifyCode(userNameEmailPhone, code);
    }
//
//    /**
//     * 设置新密码
//     */
//    @ApiOperation(value = "设置新密码", notes = "设置新密码")
//    @PostMapping("/setupNewPassword")
//    public JsonResult setupNewPassword(String phoneNum, String password) {
//        return userService.setupNewPassword(phoneNum, password);
//    }
//
    @ApiOperation("账户是否已注册,false:已被注册,true:未被注册")
    @GetMapping("userNameEmailPhoneIsRegister")
    public JsonResult userNameEmailPhoneIsRegister(String userNameEmailPhone) {
        return zuoConsumerService.userNameEmailPhoneIsRegister(userNameEmailPhone);
    }
}
