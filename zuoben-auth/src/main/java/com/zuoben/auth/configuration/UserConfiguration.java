package com.zuoben.auth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 用户token配置类
 */
@Configuration
public class UserConfiguration {
    @Value("${jwt.token-header}")
    private String userTokenHeader;

    public String getUserTokenHeader() {
        return userTokenHeader;
    }

    public void setUserTokenHeader(String userTokenHeader) {
        this.userTokenHeader = userTokenHeader;
    }
}
