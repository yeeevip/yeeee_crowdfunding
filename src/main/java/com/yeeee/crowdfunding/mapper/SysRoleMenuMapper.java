package com.yeeee.crowdfunding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeeee.crowdfunding.model.entity.SysRoleMenu;

import java.util.List;

/**
 * create by yeah.一页 2022/05/04 17:05:13
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
       List<SysRoleMenu> getList(SysRoleMenu sysRoleMenu);
       SysRoleMenu getOne(SysRoleMenu sysRoleMenu);
       int insert(SysRoleMenu sysRoleMenu);
       int updateByPrimaryKey(SysRoleMenu sysRoleMenu);
       int batchInsert(List<SysRoleMenu> sysRoleMenuList);

}




