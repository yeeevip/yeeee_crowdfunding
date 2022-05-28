package com.yeeee.crowdfunding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.model.entity.SysUser;
import com.yeeee.crowdfunding.model.vo.*;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 16:32
 */
public interface SysUserService extends IService<SysUser> {

    Oauth2TokenDTO login(String username, String password);

    PageVO<UserVO> sysUserPageList(SysUserPageReqVO sysUserPageReqVO);

    PageVO<UserVO> sysUserPageList(String query);

    boolean sysUserExist(String query);

    UserVO getUserInfo();

    Void addSysUser(SysUserEditVO editVO);

    Void editSysUser(SysUserEditVO editVO);

    SysUserInfoVO sysUserInfo(SysUserEditVO editVO);

    Void delSysUser(SysUserEditVO editVO);

    Void updSysUserPwd(SysUserUpdPwdVO userUpdPwdVO);

}
