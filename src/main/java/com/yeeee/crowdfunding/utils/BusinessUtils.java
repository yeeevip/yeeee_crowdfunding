package com.yeeee.crowdfunding.utils;

import cn.hutool.core.util.StrUtil;
import vip.yeee.memo.integrate.common.websecurity.context.SecurityContext;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/11/27 15:55
 */
public class BusinessUtils {

    public static Integer getCurUserId() {
        String curUserId = SecurityContext.getCurUserId();
        if (StrUtil.isBlank(curUserId)) {
            return null;
        }
        return Integer.valueOf(curUserId);
    }

}
