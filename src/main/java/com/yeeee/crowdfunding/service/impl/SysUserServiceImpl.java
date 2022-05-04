package com.yeeee.crowdfunding.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.SysUserConvert;
import com.yeeee.crowdfunding.mapper.SysUserMapper;
import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.model.entity.SysUser;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysUserPageReqVO;
import com.yeeee.crowdfunding.model.vo.SysUserVO;
import com.yeeee.crowdfunding.model.vo.UserVO;
import com.yeeee.crowdfunding.service.CustomUserDetailsService;
import com.yeeee.crowdfunding.service.SysUserService;
import com.yeeee.crowdfunding.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 16:32
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements SysUserService {

    private final CustomUserDetailsService userDetailsService;

    private final SysUserMapper sysUserMapper;

    private final SysUserConvert sysUserConvert;

    @Override
    public Oauth2TokenDTO login(String username, String password) {
        return userDetailsService.oauthToken(username, password, "SYSTEM");
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
    public UserVO getUserInfo() {
        UserVO userVO = sysUserConvert.securityUser2VO(SecurityUtil.currentSecurityUser());
        return userVO;
    }
}
