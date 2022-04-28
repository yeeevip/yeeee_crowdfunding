package com.yeeee.crowdfunding.model.constant;

import lombok.experimental.UtilityClass;

/**
 * 权限相关常量定义
 */
@UtilityClass
public class AuthConstant {

    /**
     * 缓存前缀
     */
    public static final String TOKEN_CACHE_PREFIX = "oauth:access:";

    /**
     * JWT存储权限前缀
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    public static final String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * Redis缓存权限规则key
     */
    public static final String RESOURCE_ROLES_MAP_KEY = "security:resourceRolesMap";

    /**
     * 匿名资源
     */
    public static final String AUTH_ANONYMOUSURLS = "security:anonymousUrls";

    /**
     * 认证信息Http请求头
     */
    public static final String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * token参数
     */
    public static final String TOKEN = "token";

    /**
     * webSocketKey
     */
    public static final String SEC_WEBSOCKET_KEY = "Sec-WebSocket-Key";

    /**
     * 用户信息Http请求头
     */
    public static final String USER_TOKEN_HEADER = "user";

    /**
     * 用户信息id
     */
    public static final String USER_ID_KEY = "id";

    /**
     * URL权限分割
     */
    public static final String AUTH_CHECK_SPLIT = ":";

    /**
     * method权限分割
     */
    public static final String AUTH_METHOD_CHECK_SPLIT = "|";

    /**
     * client_id
     */
    public static final String AUTH_CLIENT_ID = "client_id";

    /**
     * client_secret
     */
    public static final String AUTH_CLIENT_SECRET = "client_secret";

    /**
     * grant_type
     */
    public static final String AUTH_GRANT_TYPE = "grant_type";

    /**
     * username
     */
    public static final String AUTH_USERNAME = "username";

    /**
     * password
     */
    public static final String AUTH_PASSWORD = "password";

    /**
     * 授权scope
     */
    public static final String AUTH_SCOPE = "scope";

    /**
     * refresh_token
     */
    public static final String AUTH_REFRESH_TOKEN = "refresh_token";

    /**
     * client_credentials
     */
    public static final String AUTH_CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 在线 user
     */
    public static final String OAUTH_ONLINE_USERNAME = "oauth:online:user:";

    /**
     * 在线 refresh token
     */
    public static final String OAUTH_ONLINE_ACCESS_TOKEN = "oauth:online:access_token:";

    /**
     * 刷新token
     */
    public static final String OAUTH_ONLINE_REFRESH_TOKEN = "oauth:online:refresh_token:";

    /**
     * 在线token code
     */
    public static final String OAUTH_CLIENT_CODE = "oauth:client:code:";
}
