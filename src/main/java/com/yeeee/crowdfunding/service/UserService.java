package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.vo.*;
import vip.yeee.memo.integrate.common.websecurity.model.Oauth2TokenVo;

import javax.servlet.http.HttpServletRequest;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 16:18
 */
public interface UserService {

    Oauth2TokenVo login(UserCheckVO userCheckVO);

    Void logout();

    Void register(UserCheckVO userCheckVO);

    PageVO<UserVO> userPageList(UserPageReqVO userPageReqVO);

    Void updateMyselfInfo(UserVO userVO);

    UserVO getMyselfInfo();

    Void updatePassword(UpdatePasswordVO updatePasswordVO);

}
