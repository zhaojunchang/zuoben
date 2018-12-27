package com.zuoben.sys.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zuoben
 */
@FeignClient("zuoben-mq")
@Component
public interface EMailHttpClient {

	/**
	 * 发送验证码HTML邮件
	 * @param to  发送者
	 * @param code  验证码
	 * @return
	 */
	@PostMapping("api/mq/send/code")
	void sendCode(@RequestParam("to") String to,
				  @RequestParam("code") String code);

}
