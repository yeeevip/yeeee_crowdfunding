package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/28 18:12
 */
@Api(tags = "系统角色管理")
@RequiredArgsConstructor
@RestController
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @ApiOperation("角色分页")
    @GetMapping(value = "admin/sys-role/page")
    public CommonResult<PageVO<SysRoleVO>> sysRolePageList(String query) {
        return CommonResult.success(sysRoleService.sysRolePageList(query));
    }

    @ApiOperation("角色是否存在")
    @GetMapping(value = "admin/sys-role/exist")
    public CommonResult<Boolean> sysRoleExist(String query) {
        return CommonResult.success(sysRoleService.sysRoleExist(query));
    }

    @ApiOperation("创建角色")
    @PostMapping(value = "admin/sys-role/add")
    public CommonResult<Void> addSysRole(@Validated(SysRoleVO.Add.class) @RequestBody SysRoleVO editVO) {
        return CommonResult.success(sysRoleService.addSysRole(editVO));
    }

    @ApiOperation("编辑角色")
    @PostMapping(value = "admin/sys-role/upd")
    public CommonResult<Void> editSysRole(@Validated(SysRoleVO.Edit.class) @RequestBody SysRoleVO editVO) {
        return CommonResult.success(sysRoleService.editSysRole(editVO));
    }

    @ApiOperation("角色详情")
    @PostMapping(value = "admin/sys-role/info")
    public CommonResult<SysRoleVO> sysRoleInfo(@Validated(SysRoleVO.Info.class) @RequestBody SysRoleVO editVO) {
        return CommonResult.success(sysRoleService.sysRoleInfo(editVO));
    }

    @ApiOperation("删除角色")
    @PostMapping(value = "admin/sys-role/del")
    public CommonResult<Void> delSysRole(@Validated(SysRoleVO.Del.class) @RequestBody SysRoleVO editVO) {
        return CommonResult.success(sysRoleService.delSysRole(editVO));
    }

}
