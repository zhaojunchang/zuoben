package com.zuoben.exception.auth;


import com.zuoben.constant.CommonConstants;
import com.zuoben.exception.BaseException;

/**
 * Created by ace on 2017/9/8.
 */
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.TOKEN_ERROR_CODE);
    }
}
