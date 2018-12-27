package com.zuoben.constant;

/**
 * Created by ace on 2017/8/29.
 */
public class CommonConstants {
    public final static Integer SUCCESS = 0;
    /**
     * 用户token异常
     */
    public static final Integer TOKEN_ERROR_CODE = 40101;
    /**
     * 权限不足
     */
    public static final int PERMISSION_DENIED = 40201;
    /**
     * token失效
     */
    public static final Integer TOKEN_FORBIDDEN_CODE = 40301;
    /**
     * 签名错误
     */
    public static final int SIGN_ERROR_CODE = 40401;
    /**
     *
     */
    public static final Integer EX_OTHER_CODE = -3;
    /**
     * 用户id
     */
    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    /**
     * 用户名
     */
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";
    /**
     * 用户手机号
     */
    public static final String CONTEXT_KEY_USER_PHONE = "currentUserPhone";
    /**
     * 用户公司id
     */
    public static final String CONTEXT_KEY_USER_CID = "currentUserCId";
    /**
     * 用户token
     */
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    /**
     * 用户角色
     */
    public static final Integer USER_TYPE_SUPPLIER = 1;
    /**
     *
     */
    public static final Integer USER_TYPE_SELLER = 2;

    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_PHONE = "phone";
    public static final String JWT_KEY_NAME = "name";
    public static final String JWT_KEY_CID = "cId";

    /**
     * openapi加密串校验失败
     */
    public static final Integer EX_OPEN_API_INVALID_CODE = 40401;
    /**
     * 用户被禁用
     */
    public static final Integer ACCOUNT_DISABLE = 40102;

}
