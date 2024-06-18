package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.biz.CfUserBiz;
import com.yeeee.crowdfunding.model.request.CfUserEditRequest;
import com.yeeee.crowdfunding.model.request.IdsRequest;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.yeee.memo.integrate.base.websecurityoauth2.model.Oauth2TokenVo;

import javax.validation.Valid;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/28 22:53
 */
@Slf4j
@Api(tags = "前台用户管理")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final CfUserBiz cfUserBiz;

    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名", name = "username"),
            @ApiImplicitParam(value = "密码", name = "password")
    })
    @PostMapping(value = "/front/user/login")
    public CommonResult<Oauth2TokenVo> login(@Valid UserCheckVO userCheckVO) {
        return CommonResult.success(userService.login(userCheckVO));
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/front/user/updatePassword")
    public CommonResult<Void> updatePassword(@Validated @RequestBody UpdatePasswordVO updatePasswordVO) {
        return CommonResult.success(userService.updatePassword(updatePasswordVO));
    }

    @ApiOperation("用户注册")
    @PostMapping(value = "front/user/register")
    public CommonResult<Void> register(@Validated UserCheckVO userCheckVO) {
        return CommonResult.success(userService.register(userCheckVO));
    }

    @ApiOperation("修改资料")
    @PostMapping("front/user/update")
    public CommonResult<Void> updateMyselfInfo(@RequestBody UserVO userVO) {
        return CommonResult.success(userService.updateMyselfInfo(userVO));
    }

    @ApiOperation("个人资料")
    @GetMapping("front/user/info")
    public CommonResult<UserVO> getMyselfInfo() {
        return CommonResult.success(userService.getMyselfInfo());
    }

    @ApiOperation("用户分页")
    @PostMapping(value = "admin/user/page")
    public CommonResult<PageVO<UserVO>> userPageList(UserPageReqVO userPageReqVO) {
        return CommonResult.success(userService.userPageList(userPageReqVO));
    }

    @GetMapping(value = "admin/cf-user/page")
    public CommonResult<PageVO<UserVO>> cfUserPageList(String query) {
        return CommonResult.success(cfUserBiz.cfUserPageList(query));
    }

    @ApiOperation("用户是否存在")
    @GetMapping(value = "admin/cf-user/exist")
    public CommonResult<Boolean> cfUserExist(String query) {
        return CommonResult.success(cfUserBiz.cfUserExist(query));
    }

    @ApiOperation("创建用户")
    @PostMapping(value = "admin/cf-user/add")
    public CommonResult<Void> addCfUser(@Validated(CfUserEditRequest.Add.class) @RequestBody CfUserEditRequest request) {
        return CommonResult.success(cfUserBiz.addCfUser(request));
    }

    @ApiOperation("编辑用户")
    @PostMapping(value = "admin/cf-user/upd")
    public CommonResult<Void> editCfUser(@Validated(CfUserEditRequest.Edit.class) @RequestBody CfUserEditRequest request) {
        return CommonResult.success(cfUserBiz.editCfUser(request));
    }

    @ApiOperation("用户详情")
    @PostMapping(value = "admin/cf-user/info")
    public CommonResult<CfUserInfoVO> cfUserInfo(@Validated(CfUserEditRequest.Info.class) @RequestBody CfUserEditRequest request) {
        return CommonResult.success(cfUserBiz.cfUserInfo(request));
    }

    @ApiOperation("删除用户")
    @PostMapping(value = "admin/cf-user/del")
    public CommonResult<Void> delCfUser(@RequestBody IdsRequest request) {
        return CommonResult.success(cfUserBiz.delCfUser(request));
    }

}
