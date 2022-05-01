package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.UserCheckVO;
import com.yeeee.crowdfunding.model.vo.UserPageReqVO;
import com.yeeee.crowdfunding.model.vo.UserVO;
import com.yeeee.crowdfunding.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/28 22:53
 */
@Slf4j
@Api(tags = "前台用户管理", description = "前台用户管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;


    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名", name = "username"),
            @ApiImplicitParam(value = "密码", name = "password")
    })
    @PostMapping(value = "/login")
    public CommonResult<Oauth2TokenDTO> login(UserCheckVO userCheckVO) {
        return CommonResult.success(userService.login(userCheckVO));
    }

    @ApiOperation("退出登录")
    @GetMapping(value = "/logout")
    public CommonResult<Void> logout(HttpServletRequest request) {
        return CommonResult.success(userService.logout(request));
    }

    @ApiOperation("用户注册")
    @PostMapping(value = "register")
    public CommonResult<Void> register(@Validated UserCheckVO userCheckVO) {
        return CommonResult.success(userService.register(userCheckVO));
    }

    @ApiOperation("用户分页")
    @PostMapping(value = "admin/page/list")
    public CommonResult<PageVO<UserVO>> userPageList(UserPageReqVO userPageReqVO) {
        return CommonResult.success(userService.userPageList(userPageReqVO));
    }

}
