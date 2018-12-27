package com.zuoben.util.resultUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;


/**
 * @author
 * 用于封装异步调用返回结果
 */
public class JsonResult<T> {

    private static final String COMMON_ERROR_MESSAGE = "出现了一点问题，请稍后再试";
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String SUCCESS_CODE = "200";

    /**
     * 调用是否成功标识，0：成功，-2:失败, -1:非法调用
     */
    private int code;

    /**
     * 调用结果
     */
    private T data;

    /**
     * 结果消息，如果调用成功，消息通常为空
     */
    private String msg;

    private String success;

    private JsonResult() {
    }

    /**
     * @param code 代码
     * @param data 结果
     * @param msg  消息
     */
    public JsonResult(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * @param code 代码
     * @param msg  消息
     */
    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 请求成功消息
     *
     * @param data
     * @return
     */
    public static <E> JsonResult<E> success(E data) {
        return new JsonResult<E>(0, data, SUCCESS);
    }

    /**
     * 请求成功消息
     *
     * @param data
     * @return
     */
    public static <E> JsonResult<E> success(int code, E data) {
        return new JsonResult<E>(code, data, SUCCESS);
    }

    /**
     * 请求成功消息
     *
     * @return
     */
    public static <E> JsonResult<E> success() {
        return new JsonResult<E>(0, null, SUCCESS);
    }


    /**
     * 请求成功方法 ，data返回值，msg提示信息
     *
     * @param data
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> success(E data, String msg) {
        return new JsonResult<E>(0, data, msg);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> fail(String msg) {
        return new JsonResult<>(-2, null, StringUtils.isEmpty(msg) ? COMMON_ERROR_MESSAGE : msg);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> fail(Integer code, String msg) {
        return new JsonResult<>(code, null, StringUtils.isEmpty(msg) ? COMMON_ERROR_MESSAGE : msg);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> fail(Integer code, E data, String msg) {
        return new JsonResult<>(code, data, StringUtils.isEmpty(msg) ? COMMON_ERROR_MESSAGE : msg);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> unknownFail(String msg) {
        return new JsonResult<E>(-3, null, StringUtils.isEmpty(msg) ? COMMON_ERROR_MESSAGE : msg);
    }

    /**
     * 请求失败消息，根据异常类型，获取不同的提供消息
     *
     * @param throwable 异常
     * @return
     */
    public static <E> JsonResult<E> fail(Throwable throwable) {
        return fail(throwable.getMessage());
    }


    /**
     * 非法请求消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> illegal(String msg) {
        return new JsonResult<E>(-1, null, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    /**
     * 逻辑处理是否成功
     *
     * @return 是否成功
     */
    @JsonIgnore()
    public boolean isSuccess() {
        return this.code == 0 || this.code == 200;
    }
}
