package com.yeeee.crowdfunding.biz;

import com.yeeee.crowdfunding.model.entity.TUserAccount;
import com.yeeee.crowdfunding.model.request.TUserAccountFillRequest;
import com.yeeee.crowdfunding.model.vo.TUserAccountVO;
import com.yeeee.crowdfunding.service.impl.TUserAccountService;
import org.springframework.stereotype.Component;
import vip.yeee.memo.integrate.base.websecurityoauth2.context.SecurityContext;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2023/2/7 14:01
 */
@Component
public class TUserAccountBiz {

    @Resource
    private TUserAccountService tUserAccountService;

    public TUserAccountVO frontMyAccount() {
        String userId = SecurityContext.getCurUserId();
        TUserAccount account = tUserAccountService.getUserAccountByUserId(userId);
        if (account == null) {
            account = new TUserAccount();
            account.setUserId(Integer.valueOf(userId));
            account.setCreateTime(LocalDateTime.now());
            tUserAccountService.save(account);
        }
        TUserAccountVO accountVO = new TUserAccountVO();
        accountVO.setId(account.getId().toString());
        accountVO.setBalance(account.getBalance());
        accountVO.setCreateTime(account.getCreateTime());
        accountVO.setUpdateTime(account.getUpdateTime());
        return accountVO;
    }

    public Void frontFillAccount(TUserAccountFillRequest request) {
        String userId = SecurityContext.getCurUserId();
        TUserAccount account = tUserAccountService.getUserAccountByUserId(userId);
        if (account == null) {
            account = new TUserAccount();
            account.setUserId(Integer.valueOf(userId));
            account.setCreateTime(LocalDateTime.now());
            tUserAccountService.save(account);
        }
        tUserAccountService.increaseUserBalance(account, request.getBalance());
        return null;
    }
}
