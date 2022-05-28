package com.yeeee.crowdfunding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeeee.crowdfunding.model.entity.SysUserRole;

import java.util.List;

/**
 * create by yeah.一页 2022/04/29 14:13:33
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
       List<SysUserRole> getList(SysUserRole sysUserRole);
       SysUserRole getOne(SysUserRole sysUserRole);
       int insert(SysUserRole sysUserRole);
       int updateByPrimaryKey(SysUserRole sysUserRole);
       int batchInsert(List<SysUserRole> sysUserRoleList);
}




