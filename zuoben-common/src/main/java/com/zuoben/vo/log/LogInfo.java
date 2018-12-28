package com.zuoben.vo.log;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志对象
 *
 * @author
 */
public class LogInfo implements Serializable {

    private String opt;

    private String uri;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date crtTime;

    private String crtUser;

    private String crtName;

    private String crtHost;

    private String crtApiKey;

    private String crtParameter;
    private String crtBody;

    public LogInfo(String option, String uri, Date crtTime, String crtUser, String crtName, String crtHost, String crtParameter, String crtBody) {
        this.opt = option;
        this.uri = uri;
        this.crtTime = crtTime;
        this.crtUser = crtUser;
        this.crtName = crtName;
        this.crtHost = crtHost;
        this.crtParameter = crtParameter;
        this.crtBody = crtBody;
    }

    public LogInfo(String option, String uri, Date crtTime, String crtApiKey, String crtHost, String crtParameter, String crtBody) {
        this.opt = option;
        this.uri = uri;
        this.crtTime = crtTime;
        this.crtApiKey = crtApiKey;
        this.crtHost = crtHost;
        this.crtParameter = crtParameter;
        this.crtBody = crtBody;
    }

    public LogInfo(String option, String uri, Date crtTime, String crtHost, String crtParameter, String crtBody) {
        this.opt = option;
        this.uri = uri;
        this.crtTime = crtTime;
        this.crtHost = crtHost;
        this.crtParameter = crtParameter;
        this.crtBody = crtBody;
    }

    public LogInfo() {
    }

    public String getCrtParameter() {
        return crtParameter;
    }

    public void setCrtParameter(String crtParameter) {
        this.crtParameter = crtParameter;
    }

    public String getCrtBody() {
        return crtBody;
    }

    public void setCrtBody(String crtBody) {
        this.crtBody = crtBody;
    }

    public String getCrtApiKey() {
        return crtApiKey;
    }

    public void setCrtApiKey(String crtApiKey) {
        this.crtApiKey = crtApiKey;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String option) {
        this.opt = option;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public String getCrtName() {
        return crtName;
    }

    public void setCrtName(String crtName) {
        this.crtName = crtName;
    }

    public String getCrtHost() {
        return crtHost;
    }

    public void setCrtHost(String crtHost) {
        this.crtHost = crtHost;
    }
}
