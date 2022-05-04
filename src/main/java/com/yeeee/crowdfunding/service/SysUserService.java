package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysUserPageReqVO;
import com.yeeee.crowdfunding.model.vo.SysUserVO;
import com.yeeee.crowdfunding.model.vo.UserVO;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 16:32
 */
public interface SysUserService {

    Oauth2TokenDTO login(String username, String password);

    PageVO<UserVO> sysUserPageList(SysUserPageReqVO sysUserPageReqVO);

    UserVO getUserInfo();

}
