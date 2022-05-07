package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.model.vo.*;

import javax.servlet.http.HttpServletRequest;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 16:18
 */
public interface UserService {

    Oauth2TokenDTO login(UserCheckVO userCheckVO);

    Void logout();

    Void register(UserCheckVO userCheckVO);

    PageVO<UserVO> userPageList(UserPageReqVO userPageReqVO);

    Void updateMyselfInfo(UserVO userVO);

    UserVO getMyselfInfo();

    Void updatePassword(UpdatePasswordVO updatePasswordVO);

}
