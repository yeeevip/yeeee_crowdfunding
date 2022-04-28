package com.yeeee.crowdfunding.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.config.auth.AuthProperties;
import com.yeeee.crowdfunding.model.constant.AuthConstant;
import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import com.yeeee.crowdfunding.model.enums.UserBizErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/28 22:53
 */
@Slf4j
@Api(tags = "用户中心", description = "用户中心")
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final AuthProperties authProperties;
    private final TokenEndpoint tokenEndpoint;
    private final PasswordEncoder passwordEncoder;

    @ApiOperationSupport(order = 1)
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名", name = "username"),
            @ApiImplicitParam(value = "密码", name = "password")
    })
    @PostMapping(value = "/login")
    public CommonResult<Oauth2TokenDTO> login(String username, String password) throws Exception {

        Assert.isTrue(CharSequenceUtil.isAllNotBlank(username, password), UserBizErrorCode.NAME_PASSWORD_NOT_EMPTY.getMessage());

        Map<String, Object> params = new HashMap<>(5);
        params.put(AuthConstant.AUTH_CLIENT_ID, authProperties.getClientId());
        params.put(AuthConstant.AUTH_CLIENT_SECRET, authProperties.getClientSecret());
        params.put(AuthConstant.AUTH_GRANT_TYPE, authProperties.getGrantType());
        params.put(AuthConstant.AUTH_USERNAME, username);
        params.put(AuthConstant.AUTH_PASSWORD, password);

        String body = HttpUtil.post("http://127.0.0.1:8080/oauth/token", params);
        JSONObject oAuth2AccessToken = JSON.parseObject(body);

        if (StrUtil.isBlank(oAuth2AccessToken.getString("access_token"))) {
            return CommonResult.failed(body);
        }

        Oauth2TokenDTO oauth2TokenDTO = Oauth2TokenDTO.builder()
                .token(oAuth2AccessToken.getString("access_token"))
                .refreshToken(oAuth2AccessToken.getString("refresh_token"))
                .expiresIn(oAuth2AccessToken.getInteger("expires_in"))
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();

        return CommonResult.success(oauth2TokenDTO);
    }

}
