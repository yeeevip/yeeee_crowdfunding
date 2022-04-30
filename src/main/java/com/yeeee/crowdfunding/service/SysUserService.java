package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 16:32
 */
public interface SysUserService {

    Oauth2TokenDTO login(String username, String password);
}
