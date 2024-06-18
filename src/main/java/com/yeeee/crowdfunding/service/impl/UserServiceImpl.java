package com.yeeee.crowdfunding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.biz.CommonBiz;
import com.yeeee.crowdfunding.convert.UserConvert;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.UserMapper;
import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.UserService;
import com.yeeee.crowdfunding.utils.BusinessUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vip.yeee.memo.integrate.base.websecurityoauth2.constant.SecurityUserTypeEnum;
import vip.yeee.memo.integrate.base.websecurityoauth2.context.SecurityContext;
import vip.yeee.memo.integrate.base.websecurityoauth2.model.Oauth2TokenVo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 16:19
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final UserConvert userConvert;
    private final UserAuthService userAuthService;
    private final CommonBiz commonBiz;

    @Override
    public Oauth2TokenVo login(UserCheckVO userCheckVO) {
        if (!commonBiz.checkCode(userCheckVO.getCode())) {
            throw new BizException("验证码错误");
        }
        return userAuthService.getUserAccessToken(userCheckVO.getUsername(), userCheckVO.getPassword(), SecurityUserTypeEnum.FRONT_USER.getType());
    }

    @Override
    public Void logout() {
        userAuthService.userLogout(SecurityContext.getCurToken());
        return null;
    }

    @Override
    public Void register(UserCheckVO userCheckVO) {
        if (!commonBiz.checkCode(userCheckVO.getCode())) {
            throw new BizException("验证码错误");
        }
        User user = userMapper.getOne(new User().setUsername(userCheckVO.getUsername()));
        if (user != null) {
            throw new BizException("用户名已经存在！！！");
        }
        User saveUser = new User();
        saveUser.setUsername(userCheckVO.getUsername());
        saveUser.setPassword(userAuthService.encodePassword(userCheckVO.getPassword()));
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

        Integer currentUserId = BusinessUtils.getCurUserId();

        User user = userConvert.vo2Entity(userVO);
        user.setId(currentUserId);

        userMapper.updateByPrimaryKey(user);

        return null;

    }

    @Override
    public UserVO getMyselfInfo() {

        Integer currentUserId = BusinessUtils.getCurUserId();

        User user = userMapper.getOne(new User().setId(currentUserId));
        if (user == null) {
            throw new BizException("用户不存在");
        }

        return userConvert.user2VO(user);

    }

    @Override
    public Void updatePassword(UpdatePasswordVO updatePasswordVO) {

        User user = userMapper.getOne(new User().setId(BusinessUtils.getCurUserId()));
        if (user == null) {
            throw new BizException("用户不存在");
        }

        if (!userAuthService.matchPassword(updatePasswordVO.getOldPassword(), user.getPassword())) {
            throw new BizException("旧密码不正确");
        }

        User upd = new User();
        upd.setId(user.getId()).setPassword(userAuthService.encodePassword(updatePasswordVO.getNewPassword()));
        userMapper.updateByPrimaryKey(upd);

        this.logout();

        return null;
    }

}
