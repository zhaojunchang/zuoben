package com.zuoben.sys.constant;

public class SysEnum {

    private final static String UNKNOWN = "未知";

    public enum AuthType {
        /**
         * 权限菜单枚举
         */
        MENU(1, "菜单"),
        BUTTON(2, "按钮"),
        INTERFACE(3, "接口");

        private Integer val;
        private String describe;

        AuthType(Integer val, String describe) {
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
            for (AuthType authType : AuthType.values()) {
                if (authType.val.equals(val)) {
                    return authType.describe;
                }
            }
            return UNKNOWN;
        }
    }
}
