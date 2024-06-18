package com.yeeee.crowdfunding.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Sets;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.SysUserMapper;
import com.yeeee.crowdfunding.mapper.UserMapper;
import com.yeeee.crowdfunding.model.bo.FrontUserBo;
import com.yeeee.crowdfunding.model.bo.SystemUserBo;
import com.yeeee.crowdfunding.model.entity.SysUser;
import com.yeeee.crowdfunding.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import vip.yeee.memo.integrate.base.websecurityoauth2.constant.AuthConstant;
import vip.yeee.memo.integrate.base.websecurityoauth2.constant.MessageConstant;
import vip.yeee.memo.integrate.base.websecurityoauth2.model.AuthUser;
import vip.yeee.memo.integrate.base.websecurityoauth2.model.Oauth2TokenVo;
import vip.yeee.memo.integrate.common.platformauth.server.service.AbstractCustomUserDetailsService;

import javax.annotation.Resource;
import java.util.Set;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/11/16 17:34
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UserAuthService extends AbstractCustomUserDetailsService {

    private final SysUserMapper sysUserMapper;
    private final UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private TokenStore tokenStore;
    @Resource
    private OAuth2RestTemplate oAuth2RestTemplate;
    private final static String SPLIT_PATTERN = "##";

    @Override
    public AuthUser getSystemUserByUsername(String username) {
        LambdaQueryWrapper<SysUser> userQuery = Wrappers.lambdaQuery();
        userQuery.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserMapper.selectOne(userQuery);
        if (sysUser == null) {
            throw new BizException(MessageConstant.USER_NOT_EXIST);
        }
        Set<String> roles = Sets.newHashSet();
        SystemUserBo userBo = new SystemUserBo();
        userBo.setUserId(sysUser.getId().toString());
        userBo.setUsername(sysUser.getUsername());
        userBo.setPassword(sysUser.getPassword());
        userBo.setState(sysUser.getState());
        userBo.setRoles(roles);
        return userBo;
    }

    @Override
    public AuthUser getFrontUserByUsername(String username) {
        LambdaQueryWrapper<User> userQuery = Wrappers.lambdaQuery();
        userQuery.eq(User::getUsername, username);
        User user = userMapper.getOne(new User().setUsername(username));
        if (user == null) {
            throw new BizException(MessageConstant.USER_NOT_EXIST);
        }
        FrontUserBo userBo = new FrontUserBo();
        userBo.setUserId(user.getId().toString());
        userBo.setUsername(user.getUsername());
        userBo.setPassword(user.getPassword());
        userBo.setState(0);
        return userBo;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String decodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public Oauth2TokenVo getUserAccessToken(String username, String password, String userType) {
        log.info("getUserAccessToken, userType = {}, username = {}, password = {}", userType, username, password);
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            throw new vip.yeee.memo.integrate.base.model.exception.BizException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        ResourceOwnerPasswordResourceDetails passwordResourceDetails =
                (ResourceOwnerPasswordResourceDetails) this.oAuth2RestTemplate.getResource();
        passwordResourceDetails.setUsername(userType + SPLIT_PATTERN + username);
        passwordResourceDetails.setPassword(password);
        oAuth2RestTemplate.getOAuth2ClientContext().setAccessToken(null);
        OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();
        Oauth2TokenVo oauth2TokenVo = new Oauth2TokenVo();
        oauth2TokenVo.setToken(accessToken.getValue());
        oauth2TokenVo.setRefreshToken(accessToken.getRefreshToken().getValue());
        oauth2TokenVo.setExpiresIn(accessToken.getExpiresIn());
        oauth2TokenVo.setTokenHead(AuthConstant.JWT_TOKEN_PREFIX);
        return oauth2TokenVo;
    }

    public Object userLogout(String token) {
        log.info("logout");
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        // 移除access_token
        tokenStore.removeAccessToken(accessToken);
        // 移除refresh_token
        if (accessToken.getRefreshToken() != null) {
            tokenStore.removeRefreshToken(accessToken.getRefreshToken());
        }
        return null;
    }
}
