package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 16:18
 */
public interface UserService {

    Oauth2TokenDTO login(String username, String password, String userType);

    Void logout(HttpServletRequest request);

    Void register(String username, String password, String code);

}
