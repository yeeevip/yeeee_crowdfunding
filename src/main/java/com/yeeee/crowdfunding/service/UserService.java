package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.UserCheckVO;
import com.yeeee.crowdfunding.model.vo.UserPageReqVO;
import com.yeeee.crowdfunding.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 16:18
 */
public interface UserService {

    Oauth2TokenDTO login(UserCheckVO userCheckVO);

    Void logout(HttpServletRequest request);

    Void register(UserCheckVO userCheckVO);

    PageVO<UserVO> userPageList(UserPageReqVO userPageReqVO);
}
