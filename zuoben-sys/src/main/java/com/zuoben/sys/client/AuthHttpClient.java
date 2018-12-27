package com.zuoben.sys.client;

import com.zuoben.jwt.IJWTInfo;
import com.zuoben.util.resultUtils.JsonResult;
import com.zuoben.vo.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zuoben
 */
@FeignClient("zuoben-auth")
@Component
public interface AuthHttpClient {

	/**
	 * 根据token获取用户信息
	 * @param token  发送者
	 * @return
	 */
	@GetMapping("/jwt/getInfoFromToken")
	UserInfo getInfoFromToken(@RequestParam("token") String token);
}
