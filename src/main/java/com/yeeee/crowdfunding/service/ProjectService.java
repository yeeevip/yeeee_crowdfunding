package com.yeeee.crowdfunding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeee.crowdfunding.model.entity.Project;
import com.yeeee.crowdfunding.model.request.IdsRequest;
import com.yeeee.crowdfunding.model.vo.*;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 21:38
 */
public interface ProjectService extends IService<Project> {

    IndexProjectListVO getIndexShowProject();

    PageVO<ProjectVO> getProjectPageList(ProjectPageReqVO reqVO);

    PageVO<ProjectVO> getMyselfProjectList(ProjectPageReqVO reqVO);

    ProjectDetailVO getIndexProjectDetail(Integer id);

    Void lunchProject(LunchProjectVO reqVO);

    PageVO<ProjectVO> getAdminPageList(ProjectPageReqVO reqVO);

    PageVO<ProjectVO> getAdminPageList(String query);

    LunchProjectVO getAdminProjectDetail(Integer id);

    Void adminProjectAudits(AuditProjectVO auditProjectVO);

    OrderPageVO frontProjectOrderPageDetail(Integer id);

    Void updateProjectProgress(ProjectProgressVO projectProgressVO);

    Void delCfProject(IdsRequest request);

    Void updateProjectUpOrDown(ProjectUpOrDownVO request);
}
