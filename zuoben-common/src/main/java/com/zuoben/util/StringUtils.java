package com.zuoben.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chen on 2017/5/12.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 字符串工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return 字符串为空返回true否则false
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim()) || "null".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 是否至少有一个为空
     *
     * @param str
     * @return
     */
    public static boolean isBlankOneLeast(String... str) {
        for (String s : str) {
            if (isBlank(s))
                return true;
        }
        return false;
    }

    /**
     * 去掉首位字符（逗号等）
     *
     * @param str
     * @param c   去掉的字符
     */
    public static String trimChar(String str, String c) {
        if (isBlank(str) || isBlank(c))
            return trimToNull(str);

        int len = c.length();
        // 去掉之前的字符
        if (str.startsWith(c)) {
            str = str.substring(len);
        }

        // 去掉之后的字符
        if (str.endsWith(c)) {
            str = str.substring(0, str.length() - len);
        }

        return str;
    }

    /**
     * 多个逗号转换为单个逗号 并去掉首尾逗号
     *
     * @param s
     * @return
     */
    public static String dealWithComma(String s) {
        if (s.length() > 0) {
            s = s.replaceAll("[',']+", ",");// 多个,,,,替换为,
            // 去掉首尾,
            if (s.startsWith(",")) {
                s = s.substring(1);
            }
            if (s.endsWith(",")) {
                s = s.substring(0, s.length() - 1);
            }
        }
        return s;
    }

    /**
     * String[] 拼接字符串方法 包含引号
     *
     * @param s
     * @return 's1','s2','s3'
     */
    public static String genStrWithQuote(String[] s) {
        String re = "";
        for (String tmp : s) {
            re = re + "'" + tmp + "',";
        }
        return dealWithComma(re);
    }

    /**
     * String[] 拼接字符串方法 不含引号
     *
     * @param s
     * @return s1, s2, s3
     */
    public static String genStrNoQuote(String[] s) {
        return genStrWithQuote(s).replace("'", "");
    }

    /**
     * 因为系统内部存在字符串拼接SQL语句，并未使用占位符，所以需手动过滤特殊字符防止SQL注入
     *
     * @param str
     * @return
     */
    public static String TransactSQLInjection(String str) {
        return str.replaceAll("([';])+|(--)+", "");

    }

    /**
     * 获取文件名称后缀
     *
     * @param fileName 文件名称
     * @return 后缀
     */
    public static String getFileSuffix(String fileName) {
        if (isBlank(fileName) || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.indexOf(".") + 1, fileName.length());
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     * @author ：shijing
     * 2016年12月5日下午4:34:46
     */
    public static boolean isMobile(final String str) {
        Pattern p;
        Matcher m;
        boolean b;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     * @author ：shijing
     * 2016年12月5日下午4:34:21
     */
    public static boolean isPhone(final String str) {
        Pattern p1, p2;
        Matcher m;
        boolean b;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 验证是否为正确的邮箱号
     *
     * @param email 需要验证的邮箱地址
     * @return
     */
    public static boolean isValidEmail(String email) {
        // 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线 \\w 单词字符：[a-zA-Z_0-9]
        // 2、(\\w+\\.)表示域名. 如新浪邮箱域名是sina.com.cn
        // {1,3}表示可以出现一次或两次或者三次.
        String reg = "\\w+@(\\w+\\.){1,3}\\w+";
        Pattern pattern = Pattern.compile(reg);
        boolean flag = false;
        if (email != null) {
            Matcher matcher = pattern.matcher(email);
            flag = matcher.matches();
        }
        return flag;
    }

    /**
     * 将字符（保留前3位，保留后四位，中间替换为 *）用于身份证或者电话、银行卡号等敏感信息显示
     *
     * @param str
     * @return
     */
    public static String replaceSensitiveStr(String str) {
        if (isBlank(str) || str.length() < 11)
            return str;
        str = str.replaceAll("(?<=[\\d]{3})\\d(?=([\\d]|[a-z]|[A-Z]){4})", "*"); //这里*只要一个，因为会替代多次，每次一个。
        return str;
    }

    /**
     * 判断如果为null 返回空字符串
     *
     * @param obj
     * @return
     */
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }

    /**
     * 字符串根据逗号分割
     */
    public static List<Integer> strToStrArr(String str) {
        StringTokenizer toKenizer = new StringTokenizer(str, ",");
        List<Integer> list = new ArrayList<>();

        while(toKenizer.hasMoreElements()) {
            list.add(Integer.valueOf(toKenizer.nextToken()));
        }
        return  list;
    }
}
