package com.yeeee.crowdfunding.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Sets;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.SysRoleMapper;
import com.yeeee.crowdfunding.mapper.SysUserMapper;
import com.yeeee.crowdfunding.mapper.SysUserRoleMapper;
import com.yeeee.crowdfunding.mapper.UserMapper;
import com.yeeee.crowdfunding.model.bo.FrontUserBo;
import com.yeeee.crowdfunding.model.bo.SystemUserBo;
import com.yeeee.crowdfunding.model.entity.SysRole;
import com.yeeee.crowdfunding.model.entity.SysUser;
import com.yeeee.crowdfunding.model.entity.SysUserRole;
import com.yeeee.crowdfunding.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vip.yeee.memo.integrate.base.webauth.server.constant.MessageConstant;
import vip.yeee.memo.integrate.base.webauth.server.service.AbstractCustomUserDetailsService;
import vip.yeee.memo.integrate.common.websecurity.model.AuthUser;

import java.util.List;
import java.util.Set;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/11/16 17:34
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UserAuthService extends AbstractCustomUserDetailsService {

    private final SysUserMapper sysUserMapper;
    private final UserMapper userMapper;
    private final SysRoleMapper sysRoleMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    public AuthUser getSystemUserByUsername(String username) {
        LambdaQueryWrapper<SysUser> userQuery = Wrappers.lambdaQuery();
        userQuery.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserMapper.selectOne(userQuery);
        if (sysUser == null) {
            throw new BizException(MessageConstant.USER_NOT_EXIST);
        }
        // find roles
        LambdaQueryWrapper<SysUserRole> userRoleQuery = Wrappers.lambdaQuery();
        userRoleQuery.eq(SysUserRole::getUserId, sysUser.getId());
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(userRoleQuery);
        if (CollectionUtil.isEmpty(userRoles)) {
            log.warn(MessageConstant.USER_NO_ROLES);
//            throw new BizException(MessageConstant.USER_NO_ROLES);
        }
        Set<String> roles = Sets.newHashSet();
        userRoles.forEach(ur -> {
            LambdaQueryWrapper<SysRole> roleQuery = Wrappers.lambdaQuery();
            roleQuery.eq(SysRole::getId, ur.getRoleId());
            SysRole role = sysRoleMapper.selectOne(roleQuery);
            if (role != null) {
                roles.add(role.getCode());
            }
        });
        SystemUserBo userBo = new SystemUserBo();
        userBo.setUserId(sysUser.getId().toString());
        userBo.setUsername(sysUser.getUsername());
        userBo.setPassword(sysUser.getPassword());
        userBo.setState(sysUser.getState());
        userBo.setRoles(roles);
        return userBo;
    }

    @Override
    public AuthUser getFrontUserByUsername(String username) {
        LambdaQueryWrapper<User> userQuery = Wrappers.lambdaQuery();
        userQuery.eq(User::getUsername, username);
        User user = userMapper.getOne(new User().setUsername(username));
        if (user == null) {
            throw new BizException(MessageConstant.USER_NOT_EXIST);
        }
        FrontUserBo userBo = new FrontUserBo();
        userBo.setUserId(user.getId().toString());
        userBo.setUsername(user.getUsername());
        userBo.setPassword(user.getPassword());
        userBo.setState(0);
        return userBo;
    }
}
