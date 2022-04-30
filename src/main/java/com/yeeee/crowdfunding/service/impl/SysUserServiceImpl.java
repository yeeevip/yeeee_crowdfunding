package com.yeeee.crowdfunding.service.impl;

import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.service.CustomUserDetailsService;
import com.yeeee.crowdfunding.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 16:32
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements SysUserService {

    private final CustomUserDetailsService userDetailsService;

    @Override
    public Oauth2TokenDTO login(String username, String password) {
        return userDetailsService.oauthToken(username, password, "SYSTEM");
    }
}
