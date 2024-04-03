package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.SysRole;
import com.yeeee.crowdfunding.model.vo.SysRoleHasSetVO;
import com.yeeee.crowdfunding.model.vo.SysRoleVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/28 18:34
 */
@Mapper(componentModel = "spring")
public interface SysRoleConvert {

    SysRole vo2Entity(SysRoleVO sysRoleVO);

    SysRoleVO entity2VO(SysRole sysRole);

    SysRoleHasSetVO.RoleVO entity2SetVO(SysRole sysRole);

}
