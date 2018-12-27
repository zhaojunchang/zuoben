package com.zuoben.jwt;

import java.io.Serializable;

/**
 * token中储存的用户对象
 */
public class JWTInfo implements Serializable, IJWTInfo {
    private String username;
    private String userId;
    private String phone;
    private String cId;

    public JWTInfo(String username, String userId, String phone, String cId) {
        this.username = username;
        this.userId = userId;
        this.phone = phone;
        this.cId = cId;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getId() {
        return userId;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getCId() {
        return cId;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JWTInfo jwtInfo = (JWTInfo) o;

        if (username != null ? !username.equals(jwtInfo.username) : jwtInfo.username != null) {
            return false;
        }
        return userId != null ? userId.equals(jwtInfo.userId) : jwtInfo.userId == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
