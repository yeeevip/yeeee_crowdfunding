package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.ProjectCategory;
import com.yeeee.crowdfunding.model.vo.ProjectCategoryVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/1 19:57
 */
@Mapper(componentModel = "spring")
public interface ProjectCategoryConvert {

    ProjectCategoryVO entity2VO(ProjectCategory projectCategory);

}
