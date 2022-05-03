package com.yeeee.crowdfunding.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.*;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.*;
import com.yeeee.crowdfunding.model.entity.*;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.OrderService;
import com.yeeee.crowdfunding.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
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

    private final ReceiveInformationMapper receiveInformationMapper;

    private final ReceiveInfoConvert receiveInfoConvert;

    @Override
    public PageVO<BuyOrderVO> getMyselfBuyOrderList(BuyOrderPageReqVO buyOrderPageReqVO) {

        Page<Order> page = PageHelper.startPage(buyOrderPageReqVO.getPageNum(), buyOrderPageReqVO.getPageSize());

        Order query = new Order().setUserId(SecurityUtil.currentUserId());
        List<Order> orderList = orderMapper.getList(query);

        List<BuyOrderVO> buyOrderVOS = Optional.ofNullable(orderList).orElseGet(Lists::newArrayList)
                .stream()
                .map(order -> {
                    BuyOrderVO buyOrderVO = orderConvert.order2VO(order);
                    buyOrderVO.setProjectVO(Optional.ofNullable(projectConvert.project2VO(projectMapper.getOne(new Project().setId(order.getProjectId())))).orElseGet(ProjectVO::new));
                    buyOrderVO.setProjectRepayVO(Optional.ofNullable(projectRepayConvert.projectRepay2VO(projectRepayMapper.getOne(new ProjectRepay().setId(order.getProjectRepayId())))).orElseGet(ProjectRepayVO::new));
                    buyOrderVO.setSellerVO(Optional.ofNullable(userConvert.user2VO(userMapper.getOne(new User().setId(order.getUserSeller())))).orElseGet(UserVO::new));
                    return buyOrderVO;
                })
                .collect(Collectors.toList());

        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), buyOrderVOS);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Void frontCreateOrder(CreateOrderVO createOrderVO) {

        ProjectRepay projectRepay = projectRepayMapper.getOne(new ProjectRepay().setId(createOrderVO.getRepayId()));
        if (projectRepay == null) {
            throw new BizException("购买的项目不存在");
        }

        Integer currentUserId = SecurityUtil.currentUserId();
        Project project = projectMapper.getOne(new Project().setId(projectRepay.getProjectId()));

        Order order = new Order();

        ReceiveInfoVO receiveInfoVO = createOrderVO.getReceiveInfoVO();
        if (receiveInfoVO.getId() != null) {
            order.setReceiveInformation(receiveInfoVO.getId());
        } else {
            ReceiveInformation receiveInformation = new ReceiveInformation();
            receiveInformation.setUserId(currentUserId);
            receiveInformation.setReceiver(receiveInfoVO.getReceiver());
            receiveInformation.setPhone(receiveInfoVO.getPhone());
            receiveInformation.setAddress(receiveInfoVO.getAddress());
            receiveInformationMapper.insert(receiveInformation);
            order.setReceiveInformation(receiveInformation.getId());
        }

        BigDecimal costBig = BigDecimal.valueOf(projectRepay.getMoney()).multiply(BigDecimal.valueOf(createOrderVO.getPayCount()));

        order.setCode(IdUtil.simpleUUID());
        order.setProjectId(projectRepay.getProjectId());
        order.setProjectRepayId(projectRepay.getId());
        order.setUserId(currentUserId);
        order.setCount(createOrderVO.getPayCount());
        order.setOrderDate(new Date());

        order.setUserSeller(project.getUserId());
        order.setPayPrice(costBig.floatValue());

        orderMapper.insert(order);

        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Void frontPayOrder(PayVO payVO) {

        Order order = orderMapper.getOne(new Order().setId(payVO.getSubjectId()));
        if (order == null) {
            throw new BizException("订单不存在");
        }

        Project project = projectMapper.getOne(new Project().setId(order.getProjectId()));
        if (project == null) {
            throw new BizException("项目不存在");
        }

        orderMapper.updateByPrimaryKey(new Order().setId(order.getId()).setHasPay(1).setPayTime(new Date()));

        Project updProject = new Project().setId(project.getId());
        updProject.setHasFundRaising(BigDecimal.valueOf(Optional.ofNullable(project.getHasFundRaising()).orElse(0)).add(BigDecimal.valueOf(order.getPayPrice())).intValue());
        projectMapper.updateByPrimaryKey(updProject);

        return null;
    }

    @Override
    public PageVO<SellerOrderVO> getSellerOrderList(BuyOrderPageReqVO buyOrderPageReqVO) {

        Page<Order> page = PageHelper.startPage(buyOrderPageReqVO.getPageNum(), buyOrderPageReqVO.getPageSize());
        List<Order> orderList = orderMapper.getList(new Order().setUserId(SecurityUtil.currentUserId()));
        List<SellerOrderVO> orderVOList = Optional.ofNullable(orderList).orElseGet(Lists::newArrayList)
                .stream()
                .map(orderConvert::order2SellerVO)
                .peek(item -> {
                    User user = userMapper.getOne(new User().setId(item.getUserId()));
                    item.setBuyerVO(Optional.ofNullable(userConvert.user2VO(user)).orElseGet(UserVO::new));
                    Project project = projectMapper.getOne(new Project().setId(item.getProjectId()));
                    item.setProjectVO(Optional.ofNullable(projectConvert.project2VO(project)).orElseGet(ProjectVO::new));
                    ReceiveInformation receiveInformation = receiveInformationMapper.getOne(new ReceiveInformation().setId(item.getReceiveInformation()));
                    item.setReceiveInfoVO(Optional.ofNullable(receiveInfoConvert.entity2VO(receiveInformation)).orElseGet(ReceiveInfoVO::new));
                    ProjectRepay projectRepay = projectRepayMapper.getOne(new ProjectRepay().setId(item.getProjectRepayId()));
                    item.setRepayVO(Optional.ofNullable(projectRepayConvert.projectRepay2VO(projectRepay)).orElseGet(ProjectRepayVO::new));
                })
                .collect(Collectors.toList());

        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), orderVOList);
    }

    @Override
    public PageVO<SellerOrderVO> getAdminOrderPageList(BuyOrderPageReqVO buyOrderPageReqVO) {

        Page<Order> page = PageHelper.startPage(buyOrderPageReqVO.getPageNum(), buyOrderPageReqVO.getPageSize());
        List<Order> orderList = orderMapper.getList(new Order());
        List<SellerOrderVO> orderVOList = Optional.ofNullable(orderList).orElseGet(Lists::newArrayList)
                .stream()
                .map(orderConvert::order2SellerVO)
                .peek(item -> {
                    Project project = projectMapper.getOne(new Project().setId(item.getProjectId()));
                    item.setProjectVO(Optional.ofNullable(projectConvert.project2VO(project)).orElseGet(ProjectVO::new));
                    ReceiveInformation receiveInformation = receiveInformationMapper.getOne(new ReceiveInformation().setId(item.getReceiveInformation()));
                    item.setReceiveInfoVO(Optional.ofNullable(receiveInfoConvert.entity2VO(receiveInformation)).orElseGet(ReceiveInfoVO::new));
                })
                .collect(Collectors.toList());

        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), orderVOList);

    }
}
