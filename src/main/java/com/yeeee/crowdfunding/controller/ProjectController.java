package com.yeeee.crowdfunding.controller;

import com.github.pagehelper.Page;
import com.yeeee.crowdfunding.annotation.AnonymousAccess;
import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.IndexProjectListVO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.ProjectPageReqVO;
import com.yeeee.crowdfunding.model.vo.ProjectVO;
import com.yeeee.crowdfunding.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 22:38
 */
@RequiredArgsConstructor
@Api(tags = "众筹项目")
@RestController
@RequestMapping("front/project")
public class ProjectController {

    private final ProjectService projectService;

    @ApiOperation("首页项目")
    @AnonymousAccess
    @GetMapping("index")
    public CommonResult<IndexProjectListVO> getIndexShowProject() {
        return CommonResult.success(projectService.getIndexShowProject());
    }

    @ApiOperation("项目分页")
    @AnonymousAccess
    @PostMapping("list")
    public CommonResult<PageVO<ProjectVO>> getProjectList(@RequestBody ProjectPageReqVO reqVO) {
        return CommonResult.success(projectService.getProjectPageList(reqVO));
    }

    @ApiOperation("我发起的项目")
    @PostMapping("myself")
    public CommonResult<PageVO<ProjectVO>> getMyselfProjectList(@RequestBody ProjectPageReqVO reqVO) {
        return CommonResult.success(projectService.getMyselfProjectList(reqVO));
    }

}
