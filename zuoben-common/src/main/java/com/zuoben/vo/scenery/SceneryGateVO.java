package com.zuoben.vo.scenery;

import java.io.Serializable;

/**
 * @author
 */
public class SceneryGateVO implements Serializable {

    /**
     * 供应商id
     */
    private String supplierId;

    /**
     * 景区闸机请求ip
     */
    private String gateIp;

    /**
     * 闸机终端号
     */
    private String terminalNum;

    /**
     * 闸机系统与平台的apikey
     */
    private String apiKey;

    /**
     * 闸机系统与平台的apisecret
     */
    private String apiSecret;

    /**
     * 闸机识别的验证码长度
     */
    private Integer codeLength;

    /**
     * 闸机类型DY鼎游闸机   QY 秦亿闸机
     */
    private String type;

    private static final long serialVersionUID = 1L;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getGateIp() {
        return gateIp;
    }

    public void setGateIp(String gateIp) {
        this.gateIp = gateIp;
    }

    public String getTerminalNum() {
        return terminalNum;
    }

    public void setTerminalNum(String terminalNum) {
        this.terminalNum = terminalNum;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}