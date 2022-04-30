package com.yeeee.crowdfunding.mapper;

import com.yeeee.crowdfunding.model.entity.Comment;

import java.util.List;

/**
 * create by yeah.一页 2022/04/30 22:24:49
 */
public interface CommentMapper {
       List<Comment> getList(Comment comment);
       Comment getOne(Comment comment);
       int insert(Comment comment);
       int updateByPrimaryKey(Comment comment);
       int batchInsert(List<Comment> commentList);
}




