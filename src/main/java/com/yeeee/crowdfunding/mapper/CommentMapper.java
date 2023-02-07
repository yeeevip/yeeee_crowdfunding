package com.yeeee.crowdfunding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeeee.crowdfunding.model.dto.CommentDto;
import com.yeeee.crowdfunding.model.entity.Comment;
import com.yeeee.crowdfunding.model.vo.CommentVO;

import java.util.List;

/**
 * create by yeah.一页 2022/04/30 22:24:49
 */
public interface CommentMapper extends BaseMapper<Comment> {
       List<Comment> getList(Comment comment);
       Comment getOne(Comment comment);
       int insert(Comment comment);
       int updateByPrimaryKey(Comment comment);
       int batchInsert(List<Comment> commentList);

    List<CommentVO> getProjectCommentList(CommentDto query);
}




