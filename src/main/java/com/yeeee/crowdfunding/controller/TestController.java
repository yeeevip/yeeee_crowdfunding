package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.constant.AuthConstant;
import com.yeeee.crowdfunding.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/28 16:51
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public CommonResult<Object> testGet() {
        return CommonResult.success(SecurityUtil.currentSecurityUser());
    }

}
