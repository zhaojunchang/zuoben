package com.zuoben.util;

import java.util.Random;

/**
 *
 * 生成随机数
 */
public class RandomUtil {

    public static String getRandom(int length) {
        Random random = new Random();
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            boolean isChar = (random.nextInt(2) % 2 == 0);// 输出字母还是数字
            if (isChar) { // 字符串
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                ret.append((char) (choice + random.nextInt(26)));
            } else { // 数字
                ret.append(Integer.toString(random.nextInt(10)));
            }
        }
        return ret.toString();
    }

    /***
     * 生成随机数字
     *
     * @param length 生成几位参数
     *
     */
    public static String getRandomNum(int length) {
        // 验证码
        String num = "";
        for (int i = 0; i < length; i++) {
            num = num + (int) (Math.random() * 10);
        }
        return num;
    }

    /**
     * 生成随机英文字母
     *
     * @param length
     * @return
     */
    public static String getRandomLetter(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = chars.charAt((int) (Math.random() * 26));
            ret.append(c);
        }
        return ret.toString();
    }


    public static String getVcode() {
        return getRandomNum(6);
    }

}
