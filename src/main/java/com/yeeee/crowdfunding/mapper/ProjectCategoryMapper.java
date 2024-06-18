package com.yeeee.crowdfunding.mapper;

import com.yeeee.crowdfunding.model.entity.ProjectCategory;

import java.util.List;

/**
 * create by yeah.一页 2022/05/01 19:46:39
 */
public interface ProjectCategoryMapper {
       List<ProjectCategory> getList(ProjectCategory projectCategory);
       ProjectCategory getOne(ProjectCategory projectCategory);
       int insert(ProjectCategory projectCategory);
       int updateByPrimaryKey(ProjectCategory projectCategory);
       int batchInsert(List<ProjectCategory> projectCategoryList);
}




