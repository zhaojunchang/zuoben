package com.zuoben.util.resultUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

import static java.lang.String.format;

/**
 * 参数验证工具类
 */
public final class Guard {

    private Guard() {
    }

    /**
     * 如果指定的表达式为False，抛出RequestIllegalException异常
     *
     * @param expression 布尔表达式
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkThirdArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkThirdArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    /**
     * 如果指定的表达式为False，抛出RequestIllegalException异常
     *
     * @param expression   布尔表达式
     * @param errorMessage 自定义异常信息
     */
    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    /**
     * 如果指定的表达式为False，抛出RequestIllegalException异常
     *
     * @param expression           布尔表达式
     * @param errorMessageTemplate 自定义异常信息模板
     * @param errorMessageArgs     自定义异常参数
     */
    public static void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
        }
    }

    /**
     * 如果指定value 为null，抛出RequestIllegalException异常
     *
     * @param value        值
     * @param errorMessage 异常信息
     */
    public static void argumentNotNull(Object value, String errorMessage) {
        checkArgument(!Objects.equals(value, null), errorMessage);
    }

    public static void thirdArgumentNotNull(Object value, String errorMessage) {
        checkThirdArgument(!Objects.equals(value, null), errorMessage);
    }

    public static void thirdArgumentNotNullOrEmpty(String value, String errorMessage) {
        checkThirdArgument(StringUtils.isNotEmpty(value), errorMessage);
    }

    /**
     * 如果指定字符串 为null或empty，抛出RequestIllegalException异常
     *
     * @param value        字符串
     * @param errorMessage 异常信息
     */
    public static void argumentNotNullOrEmpty(String value, String errorMessage) {
        checkArgument(StringUtils.isNotEmpty(value), errorMessage);
    }

    /**
     * 如果指定的数值不在指定的数值区间，抛出RequestIllegalException异常
     *
     * @param value        指定的数值
     * @param minimum      区间最小值
     * @param maximum      区间最大值
     * @param errorMessage 异常信息
     */
    public static void argumentInRange(int value, int minimum, int maximum, String errorMessage) {
        checkArgument((minimum <= value && value <= maximum), errorMessage);
    }

    /**
     * 如果指定的数值不在指定的数值区间，抛出RequestIllegalException异常
     *
     * @param value        指定的数值
     * @param minimum      区间最小值
     * @param maximum      区间最大值
     * @param errorMessage 异常信息
     */
    public static void argumentInRange(long value, long minimum, long maximum, String errorMessage) {
        checkArgument((minimum <= value && value <= maximum), errorMessage);
    }

    /**
     * 如果指定的数值不在指定的数值区间，抛出RequestIllegalException异常
     *
     * @param value        指定的数值
     * @param minimum      区间最小值
     * @param maximum      区间最大值
     * @param errorMessage 异常信息
     */
    public static void argumentInRange(Date value, Date minimum, Date maximum, String errorMessage) {
        argumentInRange(value.getTime(), minimum.getTime(), maximum.getTime(), errorMessage);
    }
}
