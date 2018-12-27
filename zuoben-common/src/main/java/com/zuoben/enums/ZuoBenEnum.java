package com.zuoben.enums;

/**
 * 系统常用枚举
 * @author zuoben
 */
public class ZuoBenEnum {

    private final static String UNKNOWN = "未知";

    public enum State {
        /**
         * 状态枚举
         */
        SELLING(1, "启用"),
        WITHDRAW(2, "禁用"),
        DELETE(3, "删除");

        private Integer val;
        private String describe;

        State(Integer val, String describe) {
            this.val = val;
            this.describe = describe;
        }

        public Integer getVal() {
            return val;
        }

        public String getDescribe() {
            return describe;
        }

        public static String getDescByVal(Integer val) {
            for (State state : State.values()) {
                if (state.val.equals(val)) {
                    return state.describe;
                }
            }
            return UNKNOWN;
        }
    }

    public enum Sex {
        /**
         * 性别枚举
         */
        MEN(1, "男"),
        WOMEN(2, "女");

        private Integer val;
        private String describe;

        Sex(Integer val, String describe) {
            this.val = val;
            this.describe = describe;
        }

        public Integer getVal() {
            return val;
        }

        public String getDescribe() {
            return describe;
        }

        public static String getDescByVal(Integer val) {
            for (Sex sex : Sex.values()) {
                if (sex.val.equals(val)) {
                    return sex.describe;
                }
            }
            return UNKNOWN;
        }
    }

    public enum Sign {
        /**
         * 是否当前登录用户
         */
        ME(-1, "当前系统用户");

        private Integer val;
        private String describe;

        Sign(Integer val, String describe) {
            this.val = val;
            this.describe = describe;
        }

        public Integer getVal() {
            return val;
        }

        public String getDescribe() {
            return describe;
        }

        public static String getDescByVal(Integer val) {
            for (Sign sign : Sign.values()) {
                if (sign.val.equals(val)) {
                    return sign.describe;
                }
            }
            return UNKNOWN;
        }
    }

    public enum Page {
        /**
         * 是否分页查询常量
         */
        PAGE(-1, "不限制页数"),
        LIMIT(-1, "不限制数量");

        private Integer val;
        private String describe;

        Page(Integer val, String describe) {
            this.val = val;
            this.describe = describe;
        }

        public Integer getVal() {
            return val;
        }

        public String getDescribe() {
            return describe;
        }

        public static String getDescByVal(Integer val) {
            for (Page page : Page.values()) {
                if (page.val.equals(val)) {
                    return page.describe;
                }
            }
            return UNKNOWN;
        }
    }
}
