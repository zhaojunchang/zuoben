package com.zuoben.constant;

/**
 * @author gaoxiang
 * createTime:2018/4/12 0012
 */
public enum NumEnum {
    ZERO(0, "零"),
    ONE(1, "一"),
    TWO(2, "二"),
    THREE(3, "三"),
    FOUR(4, "四"),
    FIVE(5, "五"),
    SEVEN(7, "七"),
    EIGHT(8, "八"),
    NINE(9, "九"),
    TEN(10, "十");
    public Integer value;
    public String dec;

    NumEnum(Integer value, String dec) {
        this.value = value;
        this.dec = dec;
    }

    public Integer getValue() {
        return value;
    }

    public String getDec() {
        return dec;
    }

}
