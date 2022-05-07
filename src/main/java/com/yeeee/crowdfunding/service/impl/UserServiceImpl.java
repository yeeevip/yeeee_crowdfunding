package com.yeeee.crowdfunding.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.UserConvert;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.UserMapper;
import com.yeeee.crowdfunding.model.constant.AuthConstant;
import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.model.dto.auth.SecurityUser;
import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.CustomUserDetailsService;
import com.yeeee.crowdfunding.service.UserService;
import com.yeeee.crowdfunding.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private final UserConvert userConvert;

    private final PasswordEncoder passwordEncoder;

    private final TokenStore tokenStore;

    private final CustomUserDetailsService userDetailsService;

    @Override
    public Oauth2TokenDTO login(UserCheckVO userCheckVO) {
        return userDetailsService.oauthToken(userCheckVO.getUsername(), userCheckVO.getPassword(), null);
    }

    @Override
    public Void logout() {
        OAuth2AccessToken oAuth2AccessToken = SecurityUtil.getOAuth2AccessToken();
        if (oAuth2AccessToken != null) {
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
        return null;
    }

    @Override
    public Void register(UserCheckVO userCheckVO) {
        User user = userMapper.getOne(new User().setUsername(userCheckVO.getUsername()));
        if (user != null) {
            throw new BizException("用户名已经存在！！！");
        }
        User saveUser = new User();
        saveUser.setUsername(userCheckVO.getUsername());
        saveUser.setPassword(passwordEncoder.encode(userCheckVO.getPassword()));
        userMapper.insert(saveUser);
        return null;
    }

    @Override
    public PageVO<UserVO> userPageList(UserPageReqVO userPageReqVO) {
        Page<User> page = PageHelper.startPage(userPageReqVO.getPageNum(), userPageReqVO.getPageSize());
        List<UserVO> userVOList = Optional.ofNullable(userMapper.getList(new User())).orElseGet(Lists::newArrayList)
                .stream()
                .map(userConvert::user2VO)
                .collect(Collectors.toList());
        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), userVOList);
    }

    @Override
    public Void updateMyselfInfo(UserVO userVO) {

        Integer currentUserId = SecurityUtil.currentUserId();

        User user = userConvert.vo2Entity(userVO);
        user.setId(currentUserId);

        userMapper.updateByPrimaryKey(user);

        return null;

    }

    @Override
    public UserVO getMyselfInfo() {

        Integer currentUserId = SecurityUtil.currentUserId();

        User user = userMapper.getOne(new User().setId(currentUserId));
        if (user == null) {
            throw new BizException("用户不存在");
        }

        return userConvert.user2VO(user);

    }

    @Override
    public Void updatePassword(UpdatePasswordVO updatePasswordVO) {

        SecurityUser securityUser = SecurityUtil.currentSecurityUser();
        if (!passwordEncoder.matches(updatePasswordVO.getOldPassword(), securityUser.getPassword())) {
            throw new BizException("旧密码不正确");
        }

        User upd = new User();
        upd.setId(securityUser.getId()).setPassword(passwordEncoder.encode(updatePasswordVO.getNewPassword()));
        userMapper.updateByPrimaryKey(upd);

        this.logout();

        return null;
    }

}
