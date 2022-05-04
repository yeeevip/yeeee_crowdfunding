package com.yeeee.crowdfunding.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.SysMenuConvert;
import com.yeeee.crowdfunding.mapper.SysMenuMapper;
import com.yeeee.crowdfunding.mapper.SysRoleMapper;
import com.yeeee.crowdfunding.mapper.SysRoleMenuMapper;
import com.yeeee.crowdfunding.mapper.SysUserRoleMapper;
import com.yeeee.crowdfunding.model.entity.SysMenu;
import com.yeeee.crowdfunding.model.entity.SysRole;
import com.yeeee.crowdfunding.model.entity.SysRoleMenu;
import com.yeeee.crowdfunding.model.entity.SysUserRole;
import com.yeeee.crowdfunding.model.enums.SysMenuTypeEnum;
import com.yeeee.crowdfunding.model.vo.SysMenuVO;
import com.yeeee.crowdfunding.service.SysMenuService;
import com.yeeee.crowdfunding.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/4 17:08
 */
@RequiredArgsConstructor
@Service
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    private final SysUserRoleMapper sysUserRoleMapper;

    private final SysMenuConvert sysMenuConvert;

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

    private void tree(Map<Long, List<SysMenuVO>> pidMap, SysMenuVO curMenu) {
        List<SysMenuVO> child = pidMap.get(curMenu.getId());
        if (child == null) return;
        curMenu.setChildren(child);
        child.forEach(item -> {
            tree(pidMap, item);
        });
    }

}
