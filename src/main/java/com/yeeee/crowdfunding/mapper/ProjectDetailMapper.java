package com.yeeee.crowdfunding.mapper;

import com.yeeee.crowdfunding.model.entity.ProjectDetail;

import java.util.List;

/**
 * create by yeah.一页 2022/04/30 22:19:37
 */
public interface ProjectDetailMapper {
       List<ProjectDetail> getList(ProjectDetail projectDetail);
       ProjectDetail getOne(ProjectDetail projectDetail);
       int insert(ProjectDetail projectDetail);
       int updateByPrimaryKey(ProjectDetail projectDetail);
       int batchInsert(List<ProjectDetail> projectDetailList);
}




