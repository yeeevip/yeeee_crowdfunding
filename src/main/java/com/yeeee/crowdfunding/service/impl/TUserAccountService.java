package com.yeeee.crowdfunding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeeee.crowdfunding.mapper.TUserAccountMapper;
import com.yeeee.crowdfunding.model.entity.TUserAccount;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author https://www.yeee.vip
 * @since 2023-02-07
 */
@Service
public class TUserAccountService extends ServiceImpl<TUserAccountMapper, TUserAccount> {

    public TUserAccount getUserAccountByUserId(String userId) {
        LambdaQueryWrapper<TUserAccount> query = Wrappers.lambdaQuery();
        query.eq(TUserAccount::getUserId, userId);
        return this.getOne(query);
    }

    public void increaseUserBalance(TUserAccount account, Long balance) {
        LambdaUpdateWrapper<TUserAccount> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(TUserAccount::getId, account.getId());
        updateWrapper.eq(TUserAccount::getBalance, account.getBalance());
        updateWrapper.apply("balance + {0} >= 0", balance);
        updateWrapper.set(TUserAccount::getBalance, balance + account.getBalance());
        updateWrapper.set(TUserAccount::getUpdateTime, LocalDateTime.now());
        this.update(updateWrapper);
    }
}
