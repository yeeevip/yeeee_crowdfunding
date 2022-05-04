package com.yeeee.crowdfunding.controller;

import com.google.common.collect.ImmutableMap;
import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.SysMenuVO;
import com.yeeee.crowdfunding.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/4 17:09
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("menu")
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @GetMapping("admin/listTree")
    public CommonResult<List<SysMenuVO>> getMenuListTree() {
        return CommonResult.success(sysMenuService.getMenuListTree());
    }

    @GetMapping("admin/getAuthz")
    public CommonResult<Object> getMenuAuthz() {
        return CommonResult.success(ImmutableMap.of("roles", Collections.emptyList(), "stringPermissions", Collections.emptyList()));
    }

}
