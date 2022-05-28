package com.yeeee.crowdfunding.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.SysRoleConvert;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.SysRoleMapper;
import com.yeeee.crowdfunding.mapper.SysUserRoleMapper;
import com.yeeee.crowdfunding.model.entity.SysRole;
import com.yeeee.crowdfunding.model.entity.SysUserRole;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysRoleHasSetVO;
import com.yeeee.crowdfunding.model.vo.SysRoleVO;
import com.yeeee.crowdfunding.service.SysRoleService;
import com.yeeee.crowdfunding.utils.wrapper.MyPageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/28 18:09
 */
@RequiredArgsConstructor
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleConvert sysRoleConvert;

    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    public PageVO<SysRoleVO> sysRolePageList(String query) {
        MyPageWrapper<SysRole> pageWrapper = new MyPageWrapper<>(query);
        IPage<SysRole> page = this.page(pageWrapper.getPage(), pageWrapper.getQueryWrapper());
        List<SysRoleVO> roleVOList = page.getRecords()
                .stream()
                .map(sysRoleConvert::entity2VO)
                .collect(Collectors.toList());
        return new PageVO<>((int)page.getCurrent(), (int)page.getSize(), (int)page.getPages(), page.getTotal(), roleVOList);
    }

    @Override
    public boolean sysRoleExist(String query) {
        MyPageWrapper<SysRole> pageWrapper = new MyPageWrapper<>(query);
        return this.count(pageWrapper.getQueryWrapper()) > 0;
    }

    @Override
    public Void addSysRole(SysRoleVO editVO) {
        SysRole sysRole = sysRoleConvert.vo2Entity(editVO);
        this.save(sysRole);
        return null;
    }

    @Override
    public Void editSysRole(SysRoleVO editVO) {
        SysRole sysRole = this.getById(editVO.getId());
        if (sysRole == null) {
            throw new BizException("角色不存在");
        }
        SysRole upd = sysRoleConvert.vo2Entity(editVO);
        this.updateById(upd);
        return null;
    }

    @Override
    public SysRoleVO sysRoleInfo(SysRoleVO editVO) {
        SysRole sysRole = this.getById(editVO.getId());
        if (sysRole == null) {
            throw new BizException("角色不存在");
        }
        return sysRoleConvert.entity2VO(sysRole);
    }

    @Override
    public Void delSysRole(SysRoleVO editVO) {
        this.removeByIds(editVO.getIds());
        return null;
    }

    @Override
    public SysRoleHasSetVO sysRoleListAndHasSet(Integer userId) {
        SysRoleHasSetVO roleHasSetVO = new SysRoleHasSetVO();
        List<SysUserRole> userRoles = userId != null ? sysUserRoleMapper.getList(new SysUserRole().setUserId(userId)) : Collections.emptyList();
        roleHasSetVO.setCheckedKeys(Optional.ofNullable(userRoles).orElseGet(Lists::newArrayList)
                .stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toSet()));
        List<SysRole> sysRoleList = this.list();
        List<SysRoleHasSetVO.RoleVO> roleVOList = sysRoleList
                .stream()
                .map(sysRoleConvert::entity2SetVO)
                .collect(Collectors.toList());
        roleHasSetVO.setList(roleVOList);
        return roleHasSetVO;
    }
}
