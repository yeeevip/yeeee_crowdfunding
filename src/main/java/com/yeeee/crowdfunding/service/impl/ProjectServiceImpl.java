package com.yeeee.crowdfunding.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.*;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.*;
import com.yeeee.crowdfunding.model.entity.*;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.ProjectService;
import com.yeeee.crowdfunding.utils.BusinessUtils;
import com.yeeee.crowdfunding.utils.DateConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.yeee.memo.integrate.common.websecurity.context.SecurityContext;

import java.util.Arrays;
import java.util.Date;
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

    private final InitiatorInfoVOConvert initiatorInfoVOConvert;

    private final ProjectDetailConvert projectDetailConvert;

    private final ProjectRepayConvert projectRepayConvert;

    private final CommentConvert commentConvert;

    private final ProjectProgressConvert projectProgressConvert;

    private final UserConvert userConvert;

    private final OrderMapper orderMapper;

    private final ProjectDetailMapper projectDetailMapper;

    private final ProjectProgressMapper projectProgressMapper;

    private final ProjectRepayMapper projectRepayMapper;

    private final CommentMapper commentMapper;

    private final UserMapper userMapper;

    private final InitiatorPersonInfoMapper initiatorPersonInfoMapper;

    private final InitiatorCompanyInfoMapper initiatorCompanyInfoMapper;

    private final ProjectCategoryMapper projectCategoryMapper;

    private final ReceiveInformationMapper receiveInformationMapper;

    private final ReceiveInfoConvert receiveInfoConvert;

    private final ProjectCategoryConvert projectCategoryConvert;

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
        query.setUserId(BusinessUtils.getCurUserId());

        List<Project> projectList = projectMapper.getList(query);
        List<ProjectVO> result = Optional.ofNullable(projectList).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectConvert::project2VO)
                .collect(Collectors.toList());
        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), result);
    }

    @Override
    public ProjectDetailVO getIndexProjectDetail(Integer id) {

        if (id == null) {
            throw new BizException("项目ID不能为空");
        }

        Project project = projectMapper.getOne(new Project().setId(id));
        if (project == null) {
            throw new BizException("项目不存在");
        }

        ProjectDetailVO projectDetailVO = projectConvert.project2DetailVO(project);

        List<ProjectItemVO> projectItemVOS = Optional.ofNullable(projectDetailMapper.getList(new ProjectDetail().setProjectId(project.getId()))).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectDetailConvert::detail2VO)
                .collect(Collectors.toList());
        projectDetailVO.setItemVOList(projectItemVOS);

        List<ProjectRepayVO> repayVOList = Optional.ofNullable(projectRepayMapper.getList(new ProjectRepay().setProjectId(project.getId()))).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectRepayConvert::projectRepay2VO)
                .collect(Collectors.toList());
        projectDetailVO.setRepayVOList(repayVOList);

        List<CommentVO> commentVOList = Optional.ofNullable(commentMapper.getList(new Comment().setProject(project.getId()))).orElseGet(Lists::newArrayList)
                .stream()
                .map(commentConvert::comment2VO)
                .collect(Collectors.toList());
        projectDetailVO.setCommentVOList(commentVOList);

        //orderMapper.getList(new Order().setProjectId(project.getId()))

        List<ProjectProgressVO> progressVOList = Optional.ofNullable(projectProgressMapper.getList(new ProjectProgress().setProjectId(project.getId()))).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectProgressConvert::progress2VO)
                .peek(item -> item.setPublishDateStr(DateConvertUtil.timeToNow(item.getPublishDate())))
                .collect(Collectors.toList());
        projectDetailVO.setProgressVOList(progressVOList);

        User createUser = userMapper.getOne(new User().setId(project.getUserId()));
        projectDetailVO.setSellerVO(Optional.ofNullable(userConvert.user2VO(createUser)).orElseGet(UserVO::new));

        projectDetailVO.setLeftDays(DateConvertUtil.getLeftDays(project.getDaysRaising(), project.getLaunchDateRaising(), new Date()));

        return projectDetailVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Void lunchProject(LunchProjectVO reqVO) {

        Project project = projectConvert.lunchProjectVOProject(reqVO);
        project.setLaunchDateRaising(new Date());
        project.setUserId(BusinessUtils.getCurUserId());
        projectMapper.insert(project);
        if (project.getId() == null) {
            throw new BizException("发起失败");
        }

        if ("个人".equals(reqVO.getShenfen())) {
            InitiatorPersonInfo initiatorPersonInfo = initiatorInfoVOConvert.personInfoVO2Entity(reqVO.getInitiatorPersonInfoVO());
            initiatorPersonInfo.setProjectId(project.getId());
            initiatorPersonInfoMapper.insert(initiatorPersonInfo);
        } else {
            InitiatorCompanyInfo initiatorCompanyInfo = initiatorInfoVOConvert.companyInfoVO2Entity(reqVO.getInitiatorCompanyInfoVO());
            initiatorCompanyInfo.setProjectId(project.getId());
            initiatorCompanyInfoMapper.insert(initiatorCompanyInfo);
        }

        if (CollectionUtil.isNotEmpty(reqVO.getItemVOList())) {
            List<ProjectDetail> detailList = reqVO.getItemVOList()
                    .stream()
                    .map(projectDetailConvert::vo2Entity)
                    .peek(item -> item.setProjectId(project.getId()))
                    .collect(Collectors.toList());
            projectDetailMapper.batchInsert(detailList);
        }

        if (CollectionUtil.isNotEmpty(reqVO.getRepayVOList())) {
            List<ProjectRepay> repayList = reqVO.getRepayVOList()
                    .stream()
                    .map(projectRepayConvert::vo2Entity)
                    .peek(item -> item.setProjectId(project.getId()))
                    .collect(Collectors.toList());
            projectRepayMapper.batchInsert(repayList);
        }

        return null;
    }

    @Override
    public PageVO<ProjectVO> getAdminPageList(ProjectPageReqVO reqVO) {
        Page<ProjectVO> page = PageHelper.startPage(reqVO.getPageNum(), reqVO.getPageSize());

        Project query = new Project();
        if (reqVO.getProjectVO() != null) {
            query.setHasAudits(reqVO.getProjectVO().getHasAudits());
            query.setHasFinish(reqVO.getProjectVO().getHasFinish());
        }

        List<Project> projectList = projectMapper.getList(query);
        List<ProjectVO> result = Optional.ofNullable(projectList).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectConvert::project2VO)
                .peek(item -> {
                    ProjectCategory projectCategory = projectCategoryMapper.getOne(new ProjectCategory().setId(item.getProjectType()));
                    item.setCategoryVO(projectCategoryConvert.entity2VO(Optional.ofNullable(projectCategory).orElseGet(ProjectCategory::new)));
                    User user = userMapper.getOne(new User().setId(item.getUserId()));
                    item.setSeller(userConvert.user2VO(Optional.ofNullable(user).orElseGet(User::new)));
                })
                .collect(Collectors.toList());
        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), result);
    }

    @Override
    public LunchProjectVO getAdminProjectDetail(Integer id) {

        if (id == null) {
            throw new BizException("项目ID不能为空");
        }

        Project project = projectMapper.getOne(new Project().setId(id));
        if (project == null) {
            throw new BizException("项目不存在");
        }

        LunchProjectVO lunchProjectVO = projectConvert.project2LunchProjectVO(project);

        List<ProjectItemVO> projectItemVOS = Optional.ofNullable(projectDetailMapper.getList(new ProjectDetail().setProjectId(project.getId()))).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectDetailConvert::detail2VO)
                .collect(Collectors.toList());
        lunchProjectVO.setItemVOList(projectItemVOS);

        List<ProjectRepayVO> repayVOList = Optional.ofNullable(projectRepayMapper.getList(new ProjectRepay().setProjectId(project.getId()))).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectRepayConvert::projectRepay2VO)
                .collect(Collectors.toList());
        lunchProjectVO.setRepayVOList(repayVOList);

        InitiatorPersonInfo initiatorPersonInfo = initiatorPersonInfoMapper.getOne(new InitiatorPersonInfo().setProjectId(project.getId()));
        lunchProjectVO.setInitiatorPersonInfoVO(Optional.ofNullable(initiatorInfoVOConvert.entity2PersonInfoVO(initiatorPersonInfo)).orElseGet(InitiatorPersonInfoVO::new));

        InitiatorCompanyInfo initiatorCompanyInfo = initiatorCompanyInfoMapper.getOne(new InitiatorCompanyInfo().setProjectId(project.getId()));
        lunchProjectVO.setInitiatorCompanyInfoVO(Optional.ofNullable(initiatorInfoVOConvert.entity2CompanyInfoVO(initiatorCompanyInfo)).orElseGet(InitiatorCompanyInfoVO::new));

        return lunchProjectVO;
    }

    @Override
    public Void adminProjectAudits(AuditProjectVO auditProjectVO) {

        Project project = projectMapper.getOne(new Project().setId(auditProjectVO.getProjectId()));
        if (project == null) {
            throw new BizException("项目不存在");
        }

        if (Arrays.asList(-1, 1).contains(project.getHasAudits())) {
            throw new BizException("项目已经审核过了");
        }

        projectMapper.updateByPrimaryKey(new Project().setId(auditProjectVO.getProjectId()).setHasAudits(auditProjectVO.getHasAudits()));

        return null;
    }

    @Override
    public OrderPageVO frontProjectOrderPageDetail(Integer id) {

        Project project = projectMapper.getOne(new Project().setId(id));
        if (project == null) {
            throw new BizException("项目不存在");
        }
        OrderPageVO orderPageVO = new OrderPageVO();

        List<ProjectRepayVO> repayVOList = Optional.ofNullable(projectRepayMapper.getList(new ProjectRepay().setProjectId(project.getId()))).orElseGet(Lists::newArrayList)
                .stream()
                .map(projectRepayConvert::projectRepay2VO)
                .collect(Collectors.toList());
        orderPageVO.setRepayVOList(repayVOList);

        Integer currentUserId = BusinessUtils.getCurUserId();
        if (currentUserId != null) {
            ReceiveInformation receiveInformation = receiveInformationMapper.getOne(new ReceiveInformation().setUserId(currentUserId).setSetDefault(1));
            orderPageVO.setReceiveInfoVO(Optional.ofNullable(receiveInfoConvert.entity2VO(receiveInformation)).orElseGet(ReceiveInfoVO::new));
        }

        return orderPageVO;
    }

    @Override
    public Void updateProjectProgress(ProjectProgressVO projectProgressVO) {

        Project project = projectMapper.getOne(new Project().setId(projectProgressVO.getProjectId()));
        if (project == null) {
            throw new BizException("项目不存在");
        }

        ProjectProgress add = new ProjectProgress();
        add.setProjectId(project.getId());
        add.setPublishDate(new Date());
        add.setPubUser(SecurityContext.getCurUser().getUsername());
        add.setContent(projectProgressVO.getContent());
        projectProgressMapper.insert(add);

        return null;
    }

}
