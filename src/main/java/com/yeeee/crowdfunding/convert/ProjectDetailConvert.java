package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.ProjectDetail;
import com.yeeee.crowdfunding.model.vo.ProjectItemVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 22:25
 */
@Mapper(componentModel = "spring")
public interface ProjectDetailConvert {

    ProjectItemVO detail2VO(ProjectDetail detail);

    ProjectDetail vo2Entity(ProjectItemVO itemVO);

}
