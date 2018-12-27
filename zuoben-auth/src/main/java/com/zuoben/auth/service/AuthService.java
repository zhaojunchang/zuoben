package com.zuoben.auth.service;


import com.zuoben.util.resultUtils.JsonResult;

public interface AuthService {

    JsonResult<String> login(String username, String password) throws Exception;
}
