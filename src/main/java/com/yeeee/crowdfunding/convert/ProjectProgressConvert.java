package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.Comment;
import com.yeeee.crowdfunding.model.entity.ProjectProgress;
import com.yeeee.crowdfunding.model.vo.CommentVO;
import com.yeeee.crowdfunding.model.vo.ProjectProgressVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 22:25
 */
@Mapper(componentModel = "spring")
public interface ProjectProgressConvert {

    ProjectProgressVO progress2VO(ProjectProgress projectProgress);

}
