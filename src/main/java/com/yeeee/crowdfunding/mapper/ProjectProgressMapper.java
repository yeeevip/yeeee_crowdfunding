package com.yeeee.crowdfunding.mapper;

import com.yeeee.crowdfunding.model.entity.ProjectProgress;

import java.util.List;

/**
 * create by yeah.一页 2022/04/30 22:22:12
 */
public interface ProjectProgressMapper {
       List<ProjectProgress> getList(ProjectProgress projectProgress);
       ProjectProgress getOne(ProjectProgress projectProgress);
       int insert(ProjectProgress projectProgress);
       int updateByPrimaryKey(ProjectProgress projectProgress);
       int batchInsert(List<ProjectProgress> projectProgressList);
}




