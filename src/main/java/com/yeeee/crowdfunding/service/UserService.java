package com.yeeee.crowdfunding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.model.vo.*;
import vip.yeee.memo.integrate.common.websecurity.model.Oauth2TokenVo;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 16:18
 */
public interface UserService extends IService<User> {

    Oauth2TokenVo login(UserCheckVO userCheckVO);

    Void logout();

    Void register(UserCheckVO userCheckVO);

    PageVO<UserVO> userPageList(UserPageReqVO userPageReqVO);

    Void updateMyselfInfo(UserVO userVO);

    UserVO getMyselfInfo();

    Void updatePassword(UpdatePasswordVO updatePasswordVO);
}
