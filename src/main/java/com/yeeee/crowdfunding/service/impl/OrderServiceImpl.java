package com.yeeee.crowdfunding.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.OrderConvert;
import com.yeeee.crowdfunding.convert.ProjectConvert;
import com.yeeee.crowdfunding.convert.ProjectRepayConvert;
import com.yeeee.crowdfunding.convert.UserConvert;
import com.yeeee.crowdfunding.mapper.OrderMapper;
import com.yeeee.crowdfunding.mapper.ProjectMapper;
import com.yeeee.crowdfunding.mapper.ProjectRepayMapper;
import com.yeeee.crowdfunding.mapper.UserMapper;
import com.yeeee.crowdfunding.model.entity.Order;
import com.yeeee.crowdfunding.model.entity.Project;
import com.yeeee.crowdfunding.model.entity.ProjectRepay;
import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.model.vo.BuyOrderPageReqVO;
import com.yeeee.crowdfunding.model.vo.BuyOrderVO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.UserVO;
import com.yeeee.crowdfunding.service.OrderService;
import com.yeeee.crowdfunding.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 19:34
 */
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    private final ProjectMapper projectMapper;

    private final ProjectRepayMapper projectRepayMapper;

    private final UserMapper userMapper;

    private final OrderConvert orderConvert;

    private final ProjectConvert projectConvert;

    private final ProjectRepayConvert projectRepayConvert;

    private final UserConvert userConvert;

    @Override
    public PageVO<BuyOrderVO> getMyselfBuyOrderList(BuyOrderPageReqVO buyOrderPageReqVO) {

        Page<Order> page = PageHelper.startPage(buyOrderPageReqVO.getPageNum(), buyOrderPageReqVO.getPageSize());

        Order query = new Order().setUserId(SecurityUtil.currentUserId());
        List<Order> orderList = orderMapper.getList(query);

        List<BuyOrderVO> buyOrderVOS = Optional.ofNullable(orderList).orElseGet(Lists::newArrayList)
                .stream()
                .map(order -> {
                    BuyOrderVO buyOrderVO = orderConvert.order2VO(order);
                    buyOrderVO.setProjectVO(projectConvert.project2VO(projectMapper.getOne(new Project().setId(order.getProjectId()))));
                    buyOrderVO.setProjectRepayVO(projectRepayConvert.projectRepay2VO(projectRepayMapper.getOne(new ProjectRepay().setId(order.getProjectRepayId()))));
                    buyOrderVO.setSellerVO(userConvert.user2VO(userMapper.getOne(new User().setId(order.getUserSeller()))));
                    return buyOrderVO;
                })
                .collect(Collectors.toList());

        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), buyOrderVOS);
    }
}
