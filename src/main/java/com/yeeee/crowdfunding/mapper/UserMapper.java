package com.yeeee.crowdfunding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeeee.crowdfunding.model.entity.User;

import java.util.List;

/**
 * create by yeah.一页 2022/04/29 15:33:05
 */
public interface UserMapper extends BaseMapper<User> {
       List<User> getList(User user);
       User getOne(User user);
       int insert(User user);
       int updateByPrimaryKey(User user);
       int batchInsert(List<User> userList);
}




