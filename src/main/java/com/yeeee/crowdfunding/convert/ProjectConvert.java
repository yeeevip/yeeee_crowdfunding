package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.Project;
import com.yeeee.crowdfunding.model.vo.LunchProjectVO;
import com.yeeee.crowdfunding.model.vo.ProjectDetailVO;
import com.yeeee.crowdfunding.model.vo.ProjectVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 22:25
 */
@Mapper(componentModel = "spring")
public interface ProjectConvert {

    ProjectVO project2VO(Project project);

    ProjectDetailVO project2DetailVO(Project project);

    @Mappings(@Mapping(source = "projectType", target = "categoryId"))
    Project lunchProjectVOProject(LunchProjectVO lunchProjectVO);

    LunchProjectVO project2LunchProjectVO(Project project);

}
