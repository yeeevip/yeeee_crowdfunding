package com.yeeee.crowdfunding.handle;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.utils.SecurityUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理器
 */
public class AuthenticationEntryPointHandle implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        SecurityUtil.write(response, CommonResult.unauthorized(""));
    }

}
