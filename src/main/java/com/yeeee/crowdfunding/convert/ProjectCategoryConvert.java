package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.ProjectCategory;
import com.yeeee.crowdfunding.model.vo.ProjectCategoryVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/1 19:57
 */
public class ProjectCategoryConvert {

    public static ProjectCategoryVO entity2VO(ProjectCategory projectCategory) {
        if (projectCategory == null) {
            return null;
        }
        ProjectCategoryVO vo = new ProjectCategoryVO();
        vo.setId(projectCategory.getId());
        vo.setCategoryName(projectCategory.getCategoryName());
        vo.setNote(projectCategory.getNote());
        vo.setCreateDate(projectCategory.getCreateDate());
        vo.setChangeDate(projectCategory.getChangeDate());
        vo.setChangePerson(projectCategory.getChangePerson());
        return vo;
    }

}
