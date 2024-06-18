package com.yeeee.crowdfunding.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import vip.yeee.memo.integrate.base.websecurityoauth2.context.SecurityContext;

import java.util.Date;

/**
 * <p>
 * 公共字段，自动填充值
 * <p>
 * @author https://www.yeee.vip丶一页 (quhailong1995@gmail.com)
 */
@Component
public class FillMetaObjectHandler implements MetaObjectHandler {

    public static final String CRT_BY = "createBy";
    public static final String CRT_TM = "createTime";
    public static final String UPD_BY = "updateBy";
    public static final String UPD_TM = "updateTime";
    public static final String EDIT_FLAG = "delFlag";
    
    @Override
    public void insertFill(MetaObject metaObject) {
        String username = getUsername();
        Date date = new Date();
        setFieldValByName(CRT_TM, date, metaObject);
        setFieldValByName(CRT_BY, username, metaObject);
        setFieldValByName(UPD_TM, date, metaObject);
        setFieldValByName(UPD_BY, username, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        
        setFieldValByName(UPD_TM, new Date(), metaObject);
        setFieldValByName(UPD_BY, getUsername(), metaObject);

    }
    
    /**
     * 获取登陆名
     */
    private String getUsername() {
        return SecurityContext.getCurUser().getUsername();
    }

}
