package com.yeeee.crowdfunding.mapper;

import com.yeeee.crowdfunding.model.entity.SysUser;

import java.util.List;

/**
 * create by yeah.一页 2022/04/29 13:52:01
 */
public interface SysUserMapper {
       List<SysUser> getList(SysUser sysUser);
       SysUser getOne(SysUser sysUser);
       int insert(SysUser sysUser);
       int updateByPrimaryKey(SysUser sysUser);
       int batchInsert(List<SysUser> sysUserList);
}




