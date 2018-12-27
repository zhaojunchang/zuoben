package com.zuoben.sign;

import java.io.Serializable;

/**
 * @author gaoxiang
 * createTime:2018/3/20 0020
 */
public class SignInfo implements Serializable {
    /**
     * 用户key
     */
    private String apiKey;
    /**
     * 用户服务秘钥
     */
    private String apiSecret;
    /**
     * 当前时间戳(10位)
     */
    private String curTime;
    /**
     * 随机数
     */
    private String nonce;
    /**
     * 用户传入的加密串
     */
    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public SignInfo(String apiKey, String apiSecret, String curTime, String nonce, String sign) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.curTime = curTime;
        this.nonce = nonce;
        this.sign = sign;
    }
}
