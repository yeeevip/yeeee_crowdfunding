package com.yeeee.crowdfunding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeee.crowdfunding.model.entity.SysUser;
import com.yeeee.crowdfunding.model.vo.*;
import vip.yeee.memo.integrate.common.websecurity.model.Oauth2TokenVo;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 16:32
 */
public interface SysUserService extends IService<SysUser> {

    Oauth2TokenVo login(String username, String password);

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
