package com.zuoben.exception;


import com.zuoben.base.entity.ReturnDTO;
import com.zuoben.enums.HttpCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 公共 业务 异常类
 */
public class ZuobenException extends RuntimeException {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected String error;
    protected int code;
    protected ReturnDTO returnDTO;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ReturnDTO getReturnDTO() {
        return returnDTO;
    }

    public void setReturnDTO(ReturnDTO returnDTO) {
        this.returnDTO = returnDTO;
    }

    public ZuobenException(ReturnDTO returnDTO) {
        super(returnDTO.getError());
        this.returnDTO = returnDTO;
    }

    public ZuobenException(String error) {
        super(error);
        this.code = HttpCodeEnum.UN_KNOW_ERROR.getCode();
        this.error = error;
        this.returnDTO = new ReturnDTO(code, error);
    }

    public ZuobenException(int code, String error) {
        super(error);
        this.code = code;
        this.error = error;
        this.returnDTO = new ReturnDTO(code, error);
    }

    public ZuobenException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMessage());
        this.code = httpCodeEnum.getCode();
        this.error = httpCodeEnum.getMessage();
        this.returnDTO = new ReturnDTO(code, error);
    }
}
