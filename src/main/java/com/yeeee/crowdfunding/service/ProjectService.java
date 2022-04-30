package com.yeeee.crowdfunding.service;

import com.github.pagehelper.Page;
import com.yeeee.crowdfunding.model.vo.IndexProjectListVO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.ProjectPageReqVO;
import com.yeeee.crowdfunding.model.vo.ProjectVO;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 21:38
 */
public interface ProjectService {

    IndexProjectListVO getIndexShowProject();

    PageVO<ProjectVO> getProjectPageList(ProjectPageReqVO reqVO);
}
