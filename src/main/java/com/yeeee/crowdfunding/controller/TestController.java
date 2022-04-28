package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.constant.AuthConstant;
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

    private final TokenStore tokenStore;

    @GetMapping
    public CommonResult<Object> testGet(HttpServletRequest request) {
        String token = request.getHeader(AuthConstant.JWT_TOKEN_HEADER);
        String t = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(t);
        if (oAuth2AccessToken != null) {
            OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(t);
            return CommonResult.success(oAuth2Authentication.getPrincipal());
        }
        return CommonResult.failed();
    }

}
