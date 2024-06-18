package com.yeeee.crowdfunding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeeee.crowdfunding.model.entity.Order;

import java.util.List;

/**
 * create by yeah.一页 2022/04/30 19:30:13
 */
public interface OrderMapper extends BaseMapper<Order> {
       List<Order> getList(Order order);
       Order getOne(Order order);
       int insert(Order order);
       int updateByPrimaryKey(Order order);
       int batchInsert(List<Order> orderList);
}




