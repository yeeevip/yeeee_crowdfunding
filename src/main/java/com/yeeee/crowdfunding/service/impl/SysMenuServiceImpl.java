package com.yeeee.crowdfunding.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.SysMenuConvert;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.SysMenuMapper;
import com.yeeee.crowdfunding.mapper.SysRoleMapper;
import com.yeeee.crowdfunding.mapper.SysRoleMenuMapper;
import com.yeeee.crowdfunding.mapper.SysUserRoleMapper;
import com.yeeee.crowdfunding.model.entity.SysMenu;
import com.yeeee.crowdfunding.model.entity.SysRole;
import com.yeeee.crowdfunding.model.entity.SysRoleMenu;
import com.yeeee.crowdfunding.model.entity.SysUserRole;
import com.yeeee.crowdfunding.model.enums.SysMenuTypeEnum;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysMenuHasSetVO;
import com.yeeee.crowdfunding.model.vo.SysMenuVO;
import com.yeeee.crowdfunding.service.SysMenuService;
import com.yeeee.crowdfunding.utils.SecurityUtil;
import com.yeeee.crowdfunding.utils.wrapper.MyPageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/4 17:08
 */
@RequiredArgsConstructor
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    private final SysRoleMapper sysRoleMapper;

    private final SysUserRoleMapper sysUserRoleMapper;

    private final SysMenuConvert sysMenuConvert;

    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenuVO> getMenuListTree() {

        Integer userId = SecurityUtil.currentUserId();

        List<SysUserRole> userRoles = sysUserRoleMapper.getList(new SysUserRole().setUserId(userId));
        if (CollectionUtil.isEmpty(userRoles)) {
            return Collections.emptyList();
        }

        List<SysMenu> sysMenuList = sysMenuMapper.getListByRoleIds(userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList()), SysMenuTypeEnum.menu.getCode());
        if (CollectionUtil.isEmpty(sysMenuList)) {
            return Collections.emptyList();
        }

        List<SysMenuVO> sysMenuVOList = sysMenuList
                .stream()
                .map(sysMenuConvert::entity2VO)
                .collect(Collectors.toList());

        Map<Long, List<SysMenuVO>> pidMap = sysMenuVOList.stream().filter(item -> item.getPid() != null).collect(Collectors.groupingBy(SysMenuVO::getPid));

        List<SysMenuVO> parentMenu = Lists.newArrayList();
        sysMenuVOList.forEach(item -> {
            if (item.getPid() == null) {
                tree(pidMap, item);
                parentMenu.add(item);
            }
        });


        return parentMenu;
    }

    @Override
    public PageVO<SysMenuVO> getSysMenuListTreenode(String query) {
        MyPageWrapper<SysMenu> pageWrapper = new MyPageWrapper<>(query);
        QueryWrapper<SysMenu> queryWrapper = pageWrapper.getQueryWrapper();
        List<SysMenu> sysMenuList = this.list(queryWrapper);
        List<SysMenuVO> sysMenuVOList = sysMenuList
                .stream()
                .map(sysMenuConvert::entity2VO)
                .collect(Collectors.toList());
        Map<Long, List<SysMenuVO>> pidMap = sysMenuVOList.stream().filter(item -> item.getPid() != null).collect(Collectors.groupingBy(SysMenuVO::getPid));
        List<SysMenuVO> parentMenu = Lists.newArrayList();
        sysMenuVOList.forEach(item -> {
            if (item.getPid() == null) {
                tree(pidMap, item);
                parentMenu.add(item);
            }
        });
        return new PageVO<>(0, 0, 0, 0L, parentMenu);
    }

    @Override
    public Map<String, Object> getMenuAuthz() {
        List<String> roles = Lists.newArrayList();
        List<String> stringPermissions = Lists.newArrayList();
        Integer userId = SecurityUtil.currentUserId();
        List<SysUserRole> userRoles = sysUserRoleMapper.getList(new SysUserRole().setUserId(userId));
        if (CollectionUtil.isNotEmpty(userRoles)) {
            userRoles.forEach(role -> {
                SysRole sysRole = sysRoleMapper.getOne(new SysRole().setId(role.getRoleId()));
                if (sysRole != null && StrUtil.isNotEmpty(sysRole.getCode())) {
                    roles.add(sysRole.getCode());
                }
            });
        }
        List<SysMenu> sysMenuList = sysMenuMapper.getListByRoleIds(userRoles.stream()
                .map(SysUserRole::getRoleId).collect(Collectors.toList()), SysMenuTypeEnum.func.getCode());
        if (CollectionUtil.isNotEmpty(sysMenuList)) {
            sysMenuList.forEach(menu -> {
                if (StrUtil.isNotEmpty(menu.getPerm())) {
                    stringPermissions.addAll(StrUtil.split(menu.getPerm(), ','));
                }
            });
        }
        return ImmutableMap.of("roles", roles, "stringPermissions", stringPermissions);
    }

    @Override
    public Void addSysMenu(SysMenuVO editVO) {
        SysMenu sysMenu = sysMenuConvert.vo2Entity(editVO);
        this.save(sysMenu);
        return null;
    }

    @Override
    public Void editSysMenu(SysMenuVO editVO) {
        SysMenu sysMenu = this.getById(editVO.getId());
        if (sysMenu == null) {
            throw new BizException("菜单不存在");
        }
        if (editVO.getId().equals(editVO.getPid())) {
            editVO.setPid(null);
        }
        SysMenu upd = sysMenuConvert.vo2Entity(editVO);
        this.updateById(upd);
        return null;
    }

    @Override
    public SysMenuVO sysMenuInfo(SysMenuVO editVO) {
        SysMenu sysMenu = this.getById(editVO.getId());
        if (sysMenu == null) {
            throw new BizException("菜单不存在");
        }
        SysMenuVO sysMenuVO = sysMenuConvert.entity2VO(sysMenu);
        if (sysMenuVO.getPid() != null) {
            SysMenu pMenu = this.getById(sysMenuVO.getPid());
            sysMenuVO.setPnm(Optional.ofNullable(pMenu).orElseGet(SysMenu::new).getName());
        }
        return sysMenuVO;
    }

    @Override
    public Void delSysMenu(SysMenuVO editVO) {
        this.removeByIds(editVO.getIds());
        return null;
    }

    @Override
    public SysMenuHasSetVO sysMenuListAndHasSet(Integer roleId) {
        SysMenuHasSetVO sysMenuHasSetVO = new SysMenuHasSetVO();
        List<SysRoleMenu> roleMenuList = roleId != null ? sysRoleMenuMapper.getList(new SysRoleMenu().setRoleId(roleId)) : Collections.emptyList();
        sysMenuHasSetVO.setCheckedKeys(roleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toSet()));
        List<SysMenu> sysMenuList = this.list();
        List<SysMenuVO> sysMenuVOList = sysMenuList
                .stream()
                .map(sysMenuConvert::entity2VO)
                .collect(Collectors.toList());
        Map<Long, List<SysMenuVO>> pidMap = sysMenuVOList.stream().filter(item -> item.getPid() != null).collect(Collectors.groupingBy(SysMenuVO::getPid));
        List<SysMenuVO> parentMenu = Lists.newArrayList();
        sysMenuVOList.forEach(item -> {
            if (item.getPid() == null) {
                tree(pidMap, item);
                parentMenu.add(item);
            }
        });
        sysMenuHasSetVO.setList(parentMenu);
        return sysMenuHasSetVO;
    }

    private void tree(Map<Long, List<SysMenuVO>> pidMap, SysMenuVO curMenu) {
        List<SysMenuVO> child = pidMap.get(curMenu.getId());
        if (child == null) return;
        curMenu.setChildren(child);
        child.forEach(item -> {
            tree(pidMap, item);
        });
    }

}
