package com.yeeee.crowdfunding.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yeeee.crowdfunding.model.constant.AuthConstant;
import com.yeeee.crowdfunding.model.constant.CommonConstant;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SecurityUtil {

    /**
     * 返回数据封装
     * @param response:
     * @param data:
     * @return
     */
    public static void write(HttpServletResponse response, Object data) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.append(JSONObject.toJSONString(data, SerializerFeature.WriteNullStringAsEmpty));
        IoUtil.close(out);
    }

    /**
     * 浏览器信息
     *
     * @param request:
     * @return
     */
    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

    /**
     * 根据ip获取详细地址
     * 说明：外部地址读取不稳定设置超时时间
     */
    public static String getCityInfo(String ip) {
        try {
            Future<String> future = ThreadUtil.execAsync(() -> {
                String api = String.format(CommonConstant.IP_URL, ip);
                cn.hutool.json.JSONObject object = JSONUtil.parseObj(HttpUtil.get(api));
                return object.get("addr", String.class);
            });
            return future.get(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            //log.error("ip city info error", e);
        }
        return null;
    }

    /**
     * 返回当前用户ID
     * @param :
     * @return
     */
    public static Integer currentUserId(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String user = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
            return Integer.valueOf(JSON.parseObject(user).getString(AuthConstant.USER_ID_KEY));
        } catch (Exception e) {
            //log.warn("currentUserId error");
        }
        return null;
    }

/*    *//**
     * 返回token
     * @param token:
     * @return
     *//*
    public static String getClientId(String token){
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            return jwsObject.getPayload().toJSONObject().getAsString(AuthConstant.AUTH_CLIENT_ID);
        } catch (Exception e) {
            log.error("getClientId error");
        }
        return null;
    }

    *//**
     * 返回token
     * @param token:
     * @return
     *//*
    public static String getUserId(String token){
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            return jwsObject.getPayload().toJSONObject().getAsString(AuthConstant.USER_ID_KEY);
        } catch (Exception e) {
            log.error("getClientId error");
        }
        return null;
    }*/
}
