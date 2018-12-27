package com.zuoben.handler;

import com.zuoben.constant.CommonConstants;
import com.zuoben.exception.BaseException;
import com.zuoben.exception.auth.UserTokenException;
import com.zuoben.util.resultUtils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by ace on 2017/9/8.
 */
@ControllerAdvice("com.introtec")
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    public JsonResult baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(200);
        return JsonResult.fail(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult notValidExceptionHandler(HttpServletResponse response, MethodArgumentNotValidException ex) {
        response.setStatus(400);
        StringBuilder sb = new StringBuilder("参数校验错误：");
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            sb.append("[").append(error.getDefaultMessage()).append("] ");
        }
        return JsonResult.fail(sb.toString());
    }

    @ExceptionHandler(Exception.class)
    public JsonResult otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(200);
        logger.error(ex.getMessage(), ex);
        return JsonResult.fail(CommonConstants.EX_OTHER_CODE, "出了一点问题，请稍后再试");
    }

    @ExceptionHandler(UserTokenException.class)
    public JsonResult userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(200);
        logger.error(ex.getMessage(), ex);
        return JsonResult.fail(ex.getStatus(), ex.getMessage());
    }
}
