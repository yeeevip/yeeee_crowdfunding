package com.yeeee.crowdfunding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yeeee.crowdfunding.annotation.AnonymousAccess;
import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.CustomUserDetailsService;
import com.yeeee.crowdfunding.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    @PostMapping(value = "sys-user/login")
    public CommonResult<Oauth2TokenDTO> login(String username, String password) {
        return CommonResult.success(sysUserService.login(username, password));
    }

    @GetMapping("admin")
    @AnonymousAccess
    public void adminPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/pages/admin/temp.html");
    }

    @ApiOperation("用户分页")
    @PostMapping(value = "sys-user/admin/page/list")
    public CommonResult<PageVO<UserVO>> sysUserPageList(SysUserPageReqVO sysUserPageReqVO) {
        return CommonResult.success(sysUserService.sysUserPageList(sysUserPageReqVO));
    }

    @GetMapping("sys-user/admin/getUserInfo")
    public CommonResult<UserVO> getUserInfo() {
        return CommonResult.success(sysUserService.getUserInfo());
    }

}
