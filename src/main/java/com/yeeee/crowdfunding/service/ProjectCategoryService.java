package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.ProjectCategoryVO;
import com.yeeee.crowdfunding.model.vo.ProjectPageReqVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/8 16:40
 */
public interface ProjectCategoryService {

    PageVO<ProjectCategoryVO> getAdminProjectCategoryList(ProjectPageReqVO reqVO);

}
