package com.yeeee.crowdfunding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeeee.crowdfunding.model.entity.SysRole;

import java.util.List;

/**
 * create by yeah.一页 2022/04/29 14:10:14
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
       List<SysRole> getList(SysRole sysRole);
       SysRole getOne(SysRole sysRole);
       int insert(SysRole sysRole);
       int updateByPrimaryKey(SysRole sysRole);
       int batchInsert(List<SysRole> sysRoleList);
}




