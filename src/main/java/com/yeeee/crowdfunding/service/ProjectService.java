package com.yeeee.crowdfunding.service;

import com.github.pagehelper.Page;
import com.yeeee.crowdfunding.model.vo.*;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 21:38
 */
public interface ProjectService {

    IndexProjectListVO getIndexShowProject();

    PageVO<ProjectVO> getProjectPageList(ProjectPageReqVO reqVO);

    PageVO<ProjectVO> getMyselfProjectList(ProjectPageReqVO reqVO);

    ProjectDetailVO getIndexProjectDetail(Integer id);

    Void lunchProject(LunchProjectVO reqVO);

    PageVO<ProjectVO> getAdminPageList(ProjectPageReqVO reqVO);

    LunchProjectVO getAdminProjectDetail(Integer id);

    Void adminProjectAudits(AuditProjectVO auditProjectVO);

    OrderPageVO frontProjectOrderPageDetail(Integer id);

    Void updateProjectProgress(ProjectProgressVO projectProgressVO);
}
