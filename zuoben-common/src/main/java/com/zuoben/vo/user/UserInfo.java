package com.zuoben.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-21 8:12
 */
@Data
public class UserInfo {
    private String id;
    private String username;
    private String password;
    private String phonenum;
    private String apiKey;
    private String apiSecret;
    private Date updTime;
}
