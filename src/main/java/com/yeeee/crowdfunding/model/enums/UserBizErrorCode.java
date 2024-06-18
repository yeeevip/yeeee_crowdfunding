package com.yeeee.crowdfunding.model.enums;


import com.yeeee.crowdfunding.api.IErrorCode;

/**
 * API操作码
 */
public enum UserBizErrorCode implements IErrorCode {

    /**
     * 服务器异常
     */
    DEFAULT_ERROR_CODE(800000, "服务器异常"),

    /**
     * 用户名或密码不能为空
     */
    NAME_PASSWORD_NOT_EMPTY(800001, "用户名或密码不能为空"),

    /**
     * 用户或密码有误
     */
    USER_NOT_EXIST(800002, "用户或密码有误"),

    /**
     * 用户已存在
     */
    USER_EXIST(800003, "用户已存在"),

    /**
     * 用户绑定中
     */
    ROLE_ERROR(800004, "用户绑定中"),

    /**
     * 具体异常信息
     */
    SPECIFIC_ERR(800005, "具体异常信息"),

    /**
     * 用户不存在
     */
    USERNAME_NOT_EXIST(800006, "用户不存在"),

    /**
     * 登陆失效
     */
    LOGIN_CODE_ERROR(800007, "登陆失效"),
    ;

    private long code;
    private String message;

    UserBizErrorCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
