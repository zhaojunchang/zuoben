package com.zuoben.gate.feign;

import com.zuoben.util.resultUtils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "zuoben-auth")
public interface UserAuthFeign {

    @RequestMapping(value = "/jwt/userPubKey", method = RequestMethod.POST)
    JsonResult<byte[]> getUserPublicKey();
}
