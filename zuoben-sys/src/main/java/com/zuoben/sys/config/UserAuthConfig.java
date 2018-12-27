package com.zuoben.sys.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户鉴权参数配置类
 */
@Configuration
@Data
public class UserAuthConfig {
    @Value("${auth.user.token-header}")
    private String tokenHeader;
    private byte[] pubKeyByte;
    @Value("${auth.user.userId-header}")
    private String userIdHeader;
    @Value("${auth.user.userName-header}")
    private String userNameHeader;
}
