package com.zuoben.sys.service;

import com.zuoben.sys.vo.LoginRespVO;
import com.zuoben.sys.vo.RegisterReqVO;
import com.zuoben.util.resultUtils.JsonResult;

/**
 * @author
 * createTime:2018/3/16 0016
 */
public interface ZuoConsumerService {
    /**
     * 发送验证码
     */
    JsonResult sendVerifyCode(String userNameEmailPhone);

    /**
     * 账户是否已注册,false:已被注册,true:未被注册
     */
    JsonResult userNameEmailPhoneIsRegister(String userNameEmailPhone);

    /**
     * 用户注册
     */
    JsonResult<LoginRespVO> register(RegisterReqVO reqVO);

    /**
     * 校验验证码是否正确
     */
    JsonResult<Boolean> checkVerifyCode(String userNameEmailPhone, String code);
}
