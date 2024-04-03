package com.yeeee.crowdfunding.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.SysUserConvert;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.SysUserDeptMapper;
import com.yeeee.crowdfunding.mapper.SysUserMapper;
import com.yeeee.crowdfunding.mapper.SysUserRoleMapper;
import com.yeeee.crowdfunding.model.entity.SysUser;
import com.yeeee.crowdfunding.model.entity.SysUserDept;
import com.yeeee.crowdfunding.model.entity.SysUserRole;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.SysUserService;
import com.yeeee.crowdfunding.service.UserService;
import com.yeeee.crowdfunding.utils.wrapper.MyPageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.yeee.memo.integrate.common.websecurity.constant.SecurityUserTypeEnum;
import vip.yeee.memo.integrate.common.websecurity.context.SecurityContext;
import vip.yeee.memo.integrate.common.websecurity.model.Oauth2TokenVo;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 16:32
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserMapper sysUserMapper;

    private final SysUserConvert sysUserConvert;

    private final UserService userService;

    private final SysUserRoleMapper sysUserRoleMapper;

    private final SysUserDeptMapper sysUserDeptMapper;

    private final UserAuthService userAuthService;

    @Override
    public Oauth2TokenVo login(String username, String password) {
        return userAuthService.getUserAccessToken(username, password, SecurityUserTypeEnum.SYSTEM_USER.getType());
    }

    @Override
    public PageVO<UserVO> sysUserPageList(SysUserPageReqVO sysUserPageReqVO) {
        Page<SysUser> page = PageHelper.startPage(sysUserPageReqVO.getPageNum(), sysUserPageReqVO.getPageSize());
        List<UserVO> userVOList = Optional.ofNullable(sysUserMapper.getList(new SysUser())).orElseGet(Lists::newArrayList)
                .stream()
                .map(sysUserConvert::sysUser2VO)
                .collect(Collectors.toList());
        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), userVOList);
    }

    @Override
    public PageVO<UserVO> sysUserPageList(String query) {
        MyPageWrapper<SysUser> pageWrapper = new MyPageWrapper<>(query);
        IPage<SysUser> page = this.page(pageWrapper.getPage(), pageWrapper.getQueryWrapper());
        List<UserVO> userVOList = page.getRecords()
                .stream()
                .map(sysUserConvert::sysUser2VO)
                .collect(Collectors.toList());
        return new PageVO<>((int)page.getCurrent(), (int)page.getSize(), (int)page.getPages(), page.getTotal(), userVOList);
    }

    @Override
    public boolean sysUserExist(String query) {
        MyPageWrapper<SysUser> pageWrapper = new MyPageWrapper<>(query);
        return this.count(pageWrapper.getQueryWrapper()) > 0;
    }

    @Override
    public UserVO getUserInfo() {
        UserVO userVO = sysUserConvert.securityUser2VO(SecurityContext.getCurUser());
        return userVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Void addSysUser(SysUserEditVO editVO) {
        SysUser sysUser = sysUserConvert.editVO2Entity(editVO);
        sysUser.setPassword(userAuthService.encodePassword("111111"));
        this.save(sysUser);
        this.setUserRoles(editVO.getRoleIds(), sysUser.getId());
        this.setUserDepts(editVO.getOrgIds(), sysUser.getId());
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Void editSysUser(SysUserEditVO editVO) {
        Integer userId = editVO.getId();
        SysUser sysUser = this.getById(userId);
        if (sysUser == null) {
            throw new BizException("用户不存在");
        }
        SysUser upd = sysUserConvert.editVO2Entity(editVO);
        this.updateById(upd);
        sysUserRoleMapper.delete(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
        this.setUserRoles(editVO.getRoleIds(), sysUser.getId());
        sysUserDeptMapper.delete(Wrappers.<SysUserDept>lambdaQuery().eq(SysUserDept::getUserId, userId));
        this.setUserDepts(editVO.getOrgIds(), sysUser.getId());
        return null;
    }

    private void setUserRoles(Set<Integer> roleIds, Integer userId) {
        List<SysUserRole> userRoleList = roleIds
                .stream()
                .map(roleId -> {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(userId);
                    sysUserRole.setRoleId(roleId);
                    String username = SecurityContext.getCurUser().getUsername();
                    Date date = new Date();
                    sysUserRole.setCreateTime(date);
                    sysUserRole.setCreateBy(username);
                    sysUserRole.setUpdateTime(date);
                    sysUserRole.setUpdateBy(username);
                    return sysUserRole;
                })
                .collect(Collectors.toList());
        sysUserRoleMapper.batchInsert(userRoleList);
    }

    private void setUserDepts(Set<Integer> orgIds, Integer userId) {
        if (CollectionUtil.isEmpty(orgIds)) {
            return;
        }
        List<SysUserDept> userRoleList = orgIds
                .stream()
                .map(deptId -> {
                    SysUserDept sysUserDept = new SysUserDept();
                    sysUserDept.setUserId(userId);
                    sysUserDept.setDeptId(deptId);
                    return sysUserDept;
                })
                .collect(Collectors.toList());
        userRoleList.forEach(sysUserDeptMapper::insert);
    }

    @Override
    public SysUserInfoVO sysUserInfo(SysUserEditVO editVO) {
        SysUser sysUser = this.getById(editVO.getId());
        if (sysUser == null) {
            throw new BizException("用户不存在");
        }
        return sysUserConvert.entity2InfoVO(sysUser);
    }

    @Override
    public Void delSysUser(SysUserEditVO editVO) {
        this.removeByIds(editVO.getIds());
        return null;
    }

    @Override
    public Void updSysUserPwd(SysUserUpdPwdVO userUpdPwdVO) {
        SysUser sysUser = this.getById(SecurityContext.getCurUserId());
        if (sysUser == null) {
            throw new BizException("用户不存在");
        }
//        if (!userUpdPwdVO.getNewPassword1().equals(userUpdPwdVO.getNewPassword2())) {
//            throw new BizException("两次新密码前后不一致");
//        }
        if (!userAuthService.matchPassword(userUpdPwdVO.getOldPassword(), sysUser.getPassword())) {
            throw new BizException("旧密码不正确");
        }
        SysUser upd = new SysUser();
        upd.setId(sysUser.getId());
        upd.setPassword(userAuthService.encodePassword(userUpdPwdVO.getNewPassword()));
        boolean res = this.updateById(upd);
        if (res) {
            userService.logout();
        }
        return null;
    }
}
