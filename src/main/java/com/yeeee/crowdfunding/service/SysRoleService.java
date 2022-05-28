package com.yeeee.crowdfunding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeee.crowdfunding.model.entity.SysRole;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysRoleVO;
import com.yeeee.crowdfunding.model.vo.SysUserInfoVO;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/28 18:09
 */
public interface SysRoleService extends IService<SysRole> {

    PageVO<SysRoleVO> sysRolePageList(String query);

    boolean sysRoleExist(String query);

    Void addSysRole(SysRoleVO editVO);

    Void editSysRole(SysRoleVO editVO);

    SysRoleVO sysRoleInfo(SysRoleVO editVO);

    Void delSysRole(SysRoleVO editVO);

}
