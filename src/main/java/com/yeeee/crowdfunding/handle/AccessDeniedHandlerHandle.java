package com.yeeee.crowdfunding.handle;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.utils.SecurityUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理器
 */
public class AccessDeniedHandlerHandle implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        SecurityUtil.write(response, CommonResult.forbidden(""));
    }

}
