package com.zuoben.sys.service.impl;

import com.zuoben.enums.ZuoBenEnum;
import com.zuoben.sys.client.EMailHttpClient;
import com.zuoben.sys.constant.SessionKeyConst;
import com.zuoben.sys.mapper.ZuoConsumerMapper;
import com.zuoben.sys.model.ZuoConsumer;
import com.zuoben.sys.model.ZuoConsumerExample;
import com.zuoben.sys.service.RedisService;
import com.zuoben.sys.service.ZuoConsumerService;
import com.zuoben.sys.vo.LoginRespVO;
import com.zuoben.sys.vo.RegisterReqVO;
import com.zuoben.util.Md5Util;
import com.zuoben.util.RandomUtil;
import com.zuoben.util.StringUtils;
import com.zuoben.util.resultUtils.JsonResult;
import com.zuoben.util.resultUtils.SafeExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static com.zuoben.util.resultUtils.Guard.argumentNotNull;
import static com.zuoben.util.resultUtils.Guard.argumentNotNullOrEmpty;

/**
 * 用户ServerImpl
 * @author zuoben
 */
@Service
public class ZuoConsumerServiceImpl implements ZuoConsumerService {
    private Logger logger = LoggerFactory.getLogger(ZuoConsumerServiceImpl.class);

    @Value("${user.salt}")
    private String salt;
    @Resource
    ZuoConsumerMapper zuoConsumerMapper;
    @Resource
    EMailHttpClient eMailHttpClient;
    @Resource
    private RedisService redisService;

    @Override
    public JsonResult sendVerifyCode(String userNameEmailPhone) {
        return SafeExecutor.execute(() -> {
            argumentNotNullOrEmpty(userNameEmailPhone, "登录名不能为空");
            String code = RandomUtil.getVcode();

            if(StringUtils.isValidEmail(userNameEmailPhone)) {
                eMailHttpClient.sendCode(userNameEmailPhone, code);
                logger.info("发送的邮箱:{},验证码:{}", userNameEmailPhone, code);
            }
            else if(StringUtils.isPhone(userNameEmailPhone)) {
                logger.info("发送的手机号:{},验证码:{}", userNameEmailPhone, code);
            }
            else {
                logger.info("发送的账号:{}", userNameEmailPhone);
                return JsonResult.fail("用户名不合法");
            }
            //将用户手机号和验证码存入redis 有效期五分钟
            redisService.set(SessionKeyConst.USERNAME_EMAIL_PHONE + userNameEmailPhone, userNameEmailPhone, 300L);
            redisService.set(SessionKeyConst.V_CODE + userNameEmailPhone, code, 300L);
            return JsonResult.success();
        });
    }

    @Override
    public JsonResult userNameEmailPhoneIsRegister(String userNameEmailPhone) {
        return SafeExecutor.noTranExecute(() -> {
            argumentNotNullOrEmpty(userNameEmailPhone, "登录名不能为空");

            ZuoConsumerExample zuoConsumerExample = new ZuoConsumerExample();
            ZuoConsumerExample.Criteria criteria1 = zuoConsumerExample.createCriteria();
            criteria1.andEmailEqualTo(userNameEmailPhone);
            ZuoConsumerExample.Criteria criteria2 = zuoConsumerExample.createCriteria();
            criteria2.andEmailEqualTo(userNameEmailPhone);
            ZuoConsumerExample.Criteria criteria3 = zuoConsumerExample.createCriteria();
            criteria3.andEmailEqualTo(userNameEmailPhone);
            zuoConsumerExample.or(criteria1);
            zuoConsumerExample.or(criteria2);
            zuoConsumerExample.or(criteria3);

            List<ZuoConsumer> list = zuoConsumerMapper.selectByExample(zuoConsumerExample);
            if(list != null && list.size() > 0) {
                return JsonResult.fail("该账号已存在");
            }
            return JsonResult.success();
        });
    }

    @Override
    public JsonResult<LoginRespVO> register(RegisterReqVO reqVO) {
        argumentNotNull(reqVO.getPassword(), "密码不能为空");
        argumentNotNull(reqVO.getUserNameEmailPhone(), "登录名不能为空");

        JsonResult isRegister = userNameEmailPhoneIsRegister(reqVO.getUserNameEmailPhone());

        if(!isRegister.isSuccess()) {
            return isRegister;
        }

        String userNameEmailPhone = String.valueOf(redisService.get(SessionKeyConst.USERNAME_EMAIL_PHONE + reqVO.getUserNameEmailPhone()));
        String code = String.valueOf(redisService.get(SessionKeyConst.V_CODE + reqVO.getUserNameEmailPhone()));
        logger.info("接收的登录名:{},验证码:{}", userNameEmailPhone, code);
        logger.info("用户输入的的手机号:{},验证码:{}", reqVO.getUserNameEmailPhone(), reqVO.getVerifyCode());

        //验证验证码与手机
        if (code.equals(reqVO.getVerifyCode()) && userNameEmailPhone.equals(reqVO.getUserNameEmailPhone())) {
            ZuoConsumer zuoConsumer = new ZuoConsumer();

            if(StringUtils.isValidEmail(userNameEmailPhone)) {
                zuoConsumer.setEmail(reqVO.getUserNameEmailPhone());
            }
            else if(StringUtils.isPhone(userNameEmailPhone)) {
                zuoConsumer.setPhonenum(reqVO.getUserNameEmailPhone());
            }
            else {
                zuoConsumer.setLoginname(reqVO.getUserNameEmailPhone());
            }

            zuoConsumer.setPassword(Md5Util.md5(reqVO.getPassword(), salt));
            zuoConsumer.setCreateTime(new Date());
            zuoConsumer.setState(ZuoBenEnum.State.SELLING.getVal());
            zuoConsumerMapper.insertSelective(zuoConsumer);
            return JsonResult.success();
        } else {
            return JsonResult.fail("验证码不正确");
        }
    }

    @Override
    public JsonResult<Boolean> checkVerifyCode(String userNameEmailPhone, String code) {
        return SafeExecutor.noTranExecute(() -> {
            argumentNotNull(userNameEmailPhone, "登录名不能为空");
            argumentNotNull(code, "验证码不能为空");
            String redisCode = String.valueOf(redisService.get(SessionKeyConst.V_CODE + userNameEmailPhone));
            if (code.equals(redisCode)) {
                return JsonResult.success(true);
            }
            return JsonResult.success(false);
        });
    }
}
