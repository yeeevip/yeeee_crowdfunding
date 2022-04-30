package com.yeeee.crowdfunding.mapper;

import com.yeeee.crowdfunding.model.entity.ProjectRepay;

import java.util.List;

/**
 * create by yeah.一页 2022/04/30 19:58:39
 */
public interface ProjectRepayMapper {
       List<ProjectRepay> getList(ProjectRepay projectRepay);
       ProjectRepay getOne(ProjectRepay projectRepay);
       int insert(ProjectRepay projectRepay);
       int updateByPrimaryKey(ProjectRepay projectRepay);
       int batchInsert(List<ProjectRepay> projectRepayList);
}




