package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.ProjectRepay;
import com.yeeee.crowdfunding.model.vo.ProjectRepayVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 20:18
 */
@Mapper(componentModel = "spring")
public interface ProjectRepayConvert {

    ProjectRepayVO projectRepay2VO(ProjectRepay projectRepay);

    ProjectRepay vo2Entity( ProjectRepayVO projectRepayVO);

}
