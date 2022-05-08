package com.yeeee.crowdfunding.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.ProjectCategoryConvert;
import com.yeeee.crowdfunding.mapper.ProjectCategoryMapper;
import com.yeeee.crowdfunding.model.entity.ProjectCategory;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.ProjectCategoryVO;
import com.yeeee.crowdfunding.model.vo.ProjectPageReqVO;
import com.yeeee.crowdfunding.service.ProjectCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/8 16:41
 */
@RequiredArgsConstructor
@Service
public class ProjectCategoryServiceImpl implements ProjectCategoryService {

    private final ProjectCategoryMapper projectCategoryMapper;

    private final ProjectCategoryConvert projectCategoryConvert;

    @Override
    public PageVO<ProjectCategoryVO> getAdminProjectCategoryList(ProjectPageReqVO reqVO) {

        Page<ProjectCategory> page = PageHelper.startPage(reqVO.getPageNum(), reqVO.getPageSize());

        List<ProjectCategory> categoryList = projectCategoryMapper.getList(new ProjectCategory());
        List<ProjectCategoryVO> categoryVOList = Optional.ofNullable(categoryList).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectCategoryConvert::entity2VO)
                .collect(Collectors.toList());

        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), categoryVOList);

    }

}
