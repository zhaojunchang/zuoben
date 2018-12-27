package com.zuoben.auth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 用户鉴权配置类
 */
@Configuration
public class KeyConfiguration {
    /**
     * 生成rsa公私钥的密码串
     */
    @Value("${jwt.rsa-secret}")
    private String userSecret;
    /**
     * 用户鉴权公钥串
     */
    private byte[] userPubKey;
    /**
     * 用户鉴权私钥串
     */
    private byte[] userPriKey;

    public String getUserSecret() {
        return userSecret;
    }

    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
    }

    public byte[] getUserPubKey() {
        return userPubKey;
    }

    public void setUserPubKey(byte[] userPubKey) {
        this.userPubKey = userPubKey;
    }

    public byte[] getUserPriKey() {
        return userPriKey;
    }

    public void setUserPriKey(byte[] userPriKey) {
        this.userPriKey = userPriKey;
    }
}
