package com.zuoben.sign;

import com.zuoben.util.MapUtil;

import java.security.MessageDigest;
import java.util.Map;

/**
 * @author gaoxiang
 * createTime:2018/3/20 0020
 */
public class SignHelper {
    /**
     * 校验用户的加密串是否正确
     *
     * @param sha1Info
     * @return
     */
    public static Boolean checkSha1(SignInfo sha1Info) {
        Map<String, String> map = MapUtil.objectToMap(sha1Info);
        Map<String, String> tmap = MapUtil.order(map);
        if (tmap.containsKey("sign")) {
            tmap.remove("sign");
        }
        String str = MapUtil.mapJoin(tmap, false, false);
        String sha1 = getSha1(str);

        return sha1.equals(sha1Info.getSign());
    }

    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle order
            return null;
        }
    }
}
