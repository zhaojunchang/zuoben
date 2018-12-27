package com.zuoben.jwt;

import com.zuoben.sign.SignHelper;
import com.zuoben.sign.SignInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author
 * createTime:2018/3/20 0020
 * openApi鉴权
 */
@Component
public class OpenApiAuthUtil {
    Logger logger = LoggerFactory.getLogger(OpenApiAuthUtil.class);

    /**
     * 校验用户sign是否正确
     */
    public Boolean checkSign(SignInfo signInfo) throws Exception {
        if (signInfo == null || checkObjFieldIsNull(signInfo)) {
            return false;
        }
        Long curTime = Long.valueOf(signInfo.getCurTime());
        if (Math.abs(System.currentTimeMillis() / 1000 - curTime) > 600) {
            //throw new OpenApiSignException("时间戳超时,有效期十分钟");
        }
        return SignHelper.checkSha1(signInfo);
    }


    private boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            logger.info(f.getName());
            if (f.get(obj) == null) {
                return true;
            }
        }
        return false;
    }
}
