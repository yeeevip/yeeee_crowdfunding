package com.yeeee.crowdfunding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeeee.crowdfunding.model.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * create by yeah.一页 2022/04/29 21:31:09
 */
public interface ProjectMapper extends BaseMapper<Project> {
       List<Project> getList(Project project);
       Project getOne(Project project);
       int insert(Project project);
       int updateByPrimaryKey(Project project);
       int batchInsert(List<Project> projectList);
       List<Project> getOrderLimitList(@Param("project") Project project, @Param("params") Map<String, Object> params);
}




