package com.zuoben.exception.auth;


import com.zuoben.constant.CommonConstants;
import com.zuoben.exception.BaseException;

/**
 * @author gaoxiang
 */
public class OpenApiSignException extends BaseException {
    public OpenApiSignException(String message) {
        super(message, CommonConstants.SIGN_ERROR_CODE);
    }
}
