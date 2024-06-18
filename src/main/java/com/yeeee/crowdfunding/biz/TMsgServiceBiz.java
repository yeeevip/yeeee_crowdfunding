package com.yeeee.crowdfunding.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeeee.crowdfunding.convert.TMsgConvert;
import com.yeeee.crowdfunding.model.entity.TMsg;
import com.yeeee.crowdfunding.model.request.IdsRequest;
import com.yeeee.crowdfunding.model.request.PageRequest;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.TMsgListVO;
import com.yeeee.crowdfunding.service.TMsgService;
import org.springframework.stereotype.Component;
import vip.yeee.memo.integrate.base.websecurityoauth2.context.SecurityContext;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2023/2/6 18:35
 */
@Component
public class TMsgServiceBiz {

    @Resource
    private TMsgService tMsgService;

    public PageVO<TMsgListVO> frontMsgPageList(PageRequest request) {
        LambdaQueryWrapper<TMsg> query = Wrappers.lambdaQuery();
        query.orderByDesc(TMsg::getId);
        query.eq(TMsg::getTarget, SecurityContext.getCurUserId());
        query.eq(TMsg::getHasRead, 0);
        Page<TMsg> page = tMsgService.page(new Page<>(request.getPageNum(), request.getPageSize()), query);
        List<TMsgListVO> voList = page.getRecords()
                .stream()
                .map(TMsgConvert::po2ListVo)
                .collect(Collectors.toList());
        return new PageVO<>((int)page.getCurrent(), (int)page.getSize(), (int)page.getPages(), page.getTotal(), voList);
    }

    public Void frontReadMsgList(IdsRequest request) {
        if (CollectionUtil.isEmpty(request.getIds())) {
            return null;
        }
        for (Integer id : request.getIds()) {
            TMsg tMsg = new TMsg();
            tMsg.setId(Long.valueOf(id));
            tMsg.setHasRead(1);
            tMsg.setUpdateTime(LocalDateTime.now());
            tMsgService.updateById(tMsg);
        }
        return null;
    }
}
