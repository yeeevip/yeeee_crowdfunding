package com.yeeee.crowdfunding.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.OrderConvert;
import com.yeeee.crowdfunding.convert.ProjectConvert;
import com.yeeee.crowdfunding.convert.ReceiveInfoConvert;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.ReceiveInformationMapper;
import com.yeeee.crowdfunding.model.entity.Order;
import com.yeeee.crowdfunding.model.entity.Project;
import com.yeeee.crowdfunding.model.entity.ReceiveInformation;
import com.yeeee.crowdfunding.model.request.CfOrderEditRequest;
import com.yeeee.crowdfunding.model.request.IdsRequest;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.OrderService;
import com.yeeee.crowdfunding.service.ProjectService;
import com.yeeee.crowdfunding.utils.wrapper.MyPageWrapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2023/2/7 10:00
 */
@Component
public class CfOrderBiz {

    @Resource
    private OrderService orderService;
    @Resource
    private ProjectService projectService;
    @Resource
    private ReceiveInformationMapper receiveInformationMapper;
    @Resource
    private OrderConvert orderConvert;
    @Resource
    private ProjectConvert projectConvert;
    @Resource
    private ReceiveInfoConvert receiveInfoConvert;

    public PageVO<SellerOrderVO> cfUserPageList(String query) {
        MyPageWrapper<Order> wrapper = new MyPageWrapper<>(query);
        IPage<Order> page = orderService.page(wrapper.getPage(), wrapper.getQueryWrapper());
        List<SellerOrderVO> orderVOList = Optional
                .ofNullable(page.getRecords())
                .orElseGet(Lists::newArrayList)
                .stream()
                .map(orderConvert::order2SellerVO)
                .peek(item -> {
                    Project project = projectService.getById(item.getProjectId());
                    item.setProjectVO(Optional.ofNullable(projectConvert.project2VO(project)).orElseGet(ProjectVO::new));
                    ReceiveInformation receiveInformation = receiveInformationMapper.getOne(new ReceiveInformation().setId(item.getReceiveInformation()));
                    item.setReceiveInfoVO(Optional.ofNullable(receiveInfoConvert.entity2VO(receiveInformation)).orElseGet(ReceiveInfoVO::new));
                })
                .collect(Collectors.toList());
        return new PageVO<>((int)page.getCurrent(), (int)page.getSize(), (int)page.getPages(), page.getTotal(), orderVOList);
    }

    public SellerOrderVO cfUserInfo(CfOrderEditRequest request) {
        Order order = orderService.getById(request.getId());
        if (order == null) {
            throw new BizException("订单不存在");
        }
        SellerOrderVO orderVO = orderConvert.order2SellerVO(order);
        Project project = projectService.getById(orderVO.getProjectId());
        orderVO.setProjectVO(Optional.ofNullable(projectConvert.project2VO(project)).orElseGet(ProjectVO::new));
        ReceiveInformation receiveInformation = receiveInformationMapper.getOne(new ReceiveInformation().setId(orderVO.getReceiveInformation()));
        orderVO.setReceiveInfoVO(Optional.ofNullable(receiveInfoConvert.entity2VO(receiveInformation)).orElseGet(ReceiveInfoVO::new));
        return orderVO;
    }

    public Void delCfUser(IdsRequest request) {
        if (CollectionUtil.isEmpty(request.getIds())) {
            return null;
        }
        orderService.removeByIds(request.getIds());
        return null;
    }

    public Void frontConfirmReceiveOrder(PayVO payVO) {
        Order order = orderService.getById(payVO.getSubjectId());
        if (order == null) {
            throw new BizException("订单不存在");
        }
        LambdaUpdateWrapper<Order> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(Order::getId, payVO.getSubjectId());
        updateWrapper.set(Order::getHasReceive, 1);
        orderService.update(updateWrapper);
        return null;
    }

    public Void frontDeliverOrder(PayVO payVO) {
        Order order = orderService.getById(payVO.getSubjectId());
        if (order == null) {
            throw new BizException("订单不存在");
        }
        LambdaUpdateWrapper<Order> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(Order::getId, payVO.getSubjectId());
        updateWrapper.set(Order::getHasSend, 1);
        orderService.update(updateWrapper);
        return null;
    }
}
