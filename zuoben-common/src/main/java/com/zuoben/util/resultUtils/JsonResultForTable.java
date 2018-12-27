package com.zuoben.util.resultUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;


/**
 * @author gaoxiang
 * 用于封装前端分页表格异步调用返回结果
 */
public class JsonResultForTable<T> {

    public static final String COMMON_ERROR_MESSAGE = "出现了一点问题，请稍后再试";

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
    /**
     * 总条数
     */
    private long count;
    /**
     * 总页数
     */
    private Integer pages;


    /**
     * @param code  代码
     * @param data  结果
     * @param msg   消息
     * @param count 总条数
     */
    public JsonResultForTable(int code, T data, String msg, long count) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.count = count;
    }

    /**
     * @param code  代码
     * @param data  结果
     * @param msg   消息
     * @param count 总条数
     * @param pages 总页数
     */
    public JsonResultForTable(int code, T data, String msg, long count, Integer pages) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.count = count;
        this.pages = pages;
    }

    /**
     * 请求成功消息
     *
     * @param data
     * @return
     */
    public static <E> JsonResultForTable<E> success(E data, long count) {
        return new JsonResultForTable<E>(0, data, "", count);
    }

    /**
     * 请求成功消息  包含总页数
     *
     * @param data
     * @return
     */
    public static <E> JsonResultForTable<E> success(E data, long count, Integer pages) {
        return new JsonResultForTable<E>(0, data, "", count, pages);
    }

    /**
     * 请求成功方法 ，data返回值，msg提示信息
     *
     * @param data
     * @param msg
     * @return
     */
    public static <E> JsonResultForTable<E> success(E data, String msg, long count) {
        return new JsonResultForTable<E>(0, data, msg, count);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResultForTable<E> fail(String msg) {
        return new JsonResultForTable<E>(-2, null, StringUtils.isEmpty(msg) ? COMMON_ERROR_MESSAGE : msg, 0);
    }

    /**
     * 请求失败消息，根据异常类型，获取不同的提供消息
     *
     * @param throwable 异常
     * @return
     */
    public static <E> JsonResultForTable<E> fail(Throwable throwable) {
        return fail(throwable.getMessage());
    }


    /**
     * 非法请求消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResultForTable<E> illegal(String msg) {
        return new JsonResultForTable<E>(-1, null, msg, 0);
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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * 逻辑处理是否成功
     *
     * @return 是否成功
     */
    @JsonIgnore()
    public boolean isSuccess() {
        return this.code == 0;
    }
}
