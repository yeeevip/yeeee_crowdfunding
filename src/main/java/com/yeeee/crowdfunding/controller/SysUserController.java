package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.annotation.AnonymousAccess;
import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/28 22:53
 */
@Slf4j
@Api(tags = "系统用户管理", description = "系统用户管理")
@RequiredArgsConstructor
@RestController
public class SysUserController {

    private final SysUserService sysUserService;

    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名", name = "username"),
            @ApiImplicitParam(value = "密码", name = "password")
    })
    @PostMapping(value = "admin/sys-user/login")
    public CommonResult<Oauth2TokenDTO> login(String username, String password) {
        return CommonResult.success(sysUserService.login(username, password));
    }

    @GetMapping("admin")
    @AnonymousAccess
    public void adminPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/pages/admin/temp.html");
    }

    @ApiOperation("用户分页")
    @PostMapping(value = "admin/sys-user/page")
    public CommonResult<PageVO<UserVO>> sysUserPageList(SysUserPageReqVO sysUserPageReqVO) {
        return CommonResult.success(sysUserService.sysUserPageList(sysUserPageReqVO));
    }

    @ApiOperation("用户分页")
    @GetMapping(value = "admin/sys-user/page")
    public CommonResult<PageVO<UserVO>> sysUserPageList(String query) {
        return CommonResult.success(sysUserService.sysUserPageList(query));
    }

    @ApiOperation("用户是否存在")
    @GetMapping(value = "admin/sys-user/exist")
    public CommonResult<Boolean> sysUserExist(String query) {
        return CommonResult.success(sysUserService.sysUserExist(query));
    }

    @ApiOperation("创建用户")
    @PostMapping(value = "admin/sys-user/add")
    public CommonResult<Void> addSysUser(@Validated(SysUserEditVO.Add.class) @RequestBody SysUserEditVO editVO) {
        return CommonResult.success(sysUserService.addSysUser(editVO));
    }

    @ApiOperation("编辑用户")
    @PostMapping(value = "admin/sys-user/upd")
    public CommonResult<Void> editSysUser(@Validated(SysUserEditVO.Edit.class) @RequestBody SysUserEditVO editVO) {
        return CommonResult.success(sysUserService.editSysUser(editVO));
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "admin/sys-user/upd-pwd")
    public CommonResult<Void> updSysUserPwd(@RequestBody SysUserUpdPwdVO userUpdPwdVO) {
        return CommonResult.success(sysUserService.updSysUserPwd(userUpdPwdVO));
    }

    @ApiOperation("用户详情")
    @PostMapping(value = "admin/sys-user/info")
    public CommonResult<SysUserInfoVO> sysUserInfo(@Validated(SysUserEditVO.Info.class) @RequestBody SysUserEditVO editVO) {
        return CommonResult.success(sysUserService.sysUserInfo(editVO));
    }

    @ApiOperation("删除用户")
    @PostMapping(value = "admin/sys-user/del")
    public CommonResult<Void> delSysUser(@Validated(SysUserEditVO.Info.class) @RequestBody SysUserEditVO editVO) {
        return CommonResult.success(sysUserService.delSysUser(editVO));
    }

    @GetMapping("admin/sys-user/getUserInfo")
    public CommonResult<UserVO> getUserInfo() {
        return CommonResult.success(sysUserService.getUserInfo());
    }

}
