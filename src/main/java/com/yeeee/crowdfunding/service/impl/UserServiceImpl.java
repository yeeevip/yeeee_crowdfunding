package com.yeeee.crowdfunding.service.impl;

import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.UserMapper;
import com.yeeee.crowdfunding.model.constant.AuthConstant;
import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.service.CustomUserDetailsService;
import com.yeeee.crowdfunding.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 16:19
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final TokenStore tokenStore;

    private final CustomUserDetailsService userDetailsService;

    @Override
    public Oauth2TokenDTO login(String username, String password, String userType) {
        return userDetailsService.oauthToken(username, password, null);
    }

    @Override
    public Void logout(HttpServletRequest request) {
        String token = request.getHeader(AuthConstant.JWT_TOKEN_HEADER);
        String t = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(t);
        if (oAuth2AccessToken != null) {
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
        return null;
    }

    @Override
    public Void register(String username, String password, String code) {
        User user = userMapper.getOne(new User().setUsername(username));
        if (user != null) {
            throw new BizException("用户名已经存在！！！");
        }
        User saveUser = new User();
        saveUser.setUsername(username);
        saveUser.setPassword(passwordEncoder.encode(password));
        userMapper.insert(saveUser);
        return null;
    }

}
