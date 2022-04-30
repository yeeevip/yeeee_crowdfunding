package com.yeeee.crowdfunding.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.ProjectConvert;
import com.yeeee.crowdfunding.mapper.ProjectMapper;
import com.yeeee.crowdfunding.model.entity.Project;
import com.yeeee.crowdfunding.model.vo.IndexProjectListVO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.ProjectPageReqVO;
import com.yeeee.crowdfunding.model.vo.ProjectVO;
import com.yeeee.crowdfunding.service.ProjectService;
import com.yeeee.crowdfunding.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 21:38
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;

    private final ProjectConvert projectConvert;

    @Override
    public IndexProjectListVO getIndexShowProject() {

        IndexProjectListVO indexProjectListVO = new IndexProjectListVO();

        // 热门
        List<Project> hotList = projectMapper.getOrderLimitList(new Project().setHasIndex(1)
                , ImmutableMap.of("orderField", "has_fund_raising", "orderSort", "desc", "limit", 3));
        List<ProjectVO> hotVOList = Optional.ofNullable(hotList).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectConvert::project2VO)
                .collect(Collectors.toList());
        indexProjectListVO.setHotList(hotVOList);


        // 公益
        List<Project> welfareList = projectMapper.getOrderLimitList(new Project().setCategoryId(1)
                , ImmutableMap.of("orderField", "has_fund_raising", "orderSort", "desc", "limit", 3));
        List<ProjectVO> welfareVOList = Optional.ofNullable(welfareList).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectConvert::project2VO)
                .collect(Collectors.toList());
        indexProjectListVO.setWelfareList(welfareVOList);

        // 农业
        List<Project> agList = projectMapper.getOrderLimitList(new Project().setCategoryId(2)
                , ImmutableMap.of("orderField", "has_fund_raising", "orderSort", "desc", "limit", 3));
        List<ProjectVO> agVOList = Optional.ofNullable(agList).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectConvert::project2VO)
                .collect(Collectors.toList());
        indexProjectListVO.setAgList(agVOList);

        // 出版
        List<Project> publishList = projectMapper.getOrderLimitList(new Project().setCategoryId(3)
                , ImmutableMap.of("orderField", "has_fund_raising", "orderSort", "desc", "limit", 3));
        List<ProjectVO> publishVOList = Optional.ofNullable(publishList).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectConvert::project2VO)
                .collect(Collectors.toList());
        indexProjectListVO.setPublishList(publishVOList);

        // 艺术
        List<Project> artList = projectMapper.getOrderLimitList(new Project().setCategoryId(4)
                , ImmutableMap.of("orderField", "has_fund_raising", "orderSort", "desc", "limit", 3));
        List<ProjectVO> artVOList = Optional.ofNullable(artList).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectConvert::project2VO)
                .collect(Collectors.toList());
        indexProjectListVO.setArtList(artVOList);

        return indexProjectListVO;
    }

    @Override
    public PageVO<ProjectVO> getProjectPageList(ProjectPageReqVO reqVO) {
        Page<ProjectVO> page = PageHelper.startPage(reqVO.getPageNum(), reqVO.getPageSize());

        Project query = new Project();
        if (reqVO.getProjectVO() != null) {
            query.setKeyword(reqVO.getProjectVO().getKeyword())
                .setCategoryId(reqVO.getProjectVO().getProjectType());
        }
        query.setHasAudits(1);

        List<Project> projectList = projectMapper.getList(query);
        List<ProjectVO> result = Optional.ofNullable(projectList).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectConvert::project2VO)
                .collect(Collectors.toList());
        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), result);
    }

    @Override
    public PageVO<ProjectVO> getMyselfProjectList(ProjectPageReqVO reqVO) {
        Page<ProjectVO> page = PageHelper.startPage(reqVO.getPageNum(), 5);

        Project query = new Project();
        query.setUserId(SecurityUtil.currentUserId());

        List<Project> projectList = projectMapper.getList(query);
        List<ProjectVO> result = Optional.ofNullable(projectList).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectConvert::project2VO)
                .collect(Collectors.toList());
        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), result);
    }
}
