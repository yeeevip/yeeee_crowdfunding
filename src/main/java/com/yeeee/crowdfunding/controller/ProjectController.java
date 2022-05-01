package com.yeeee.crowdfunding.controller;

import com.github.pagehelper.Page;
import com.yeeee.crowdfunding.annotation.AnonymousAccess;
import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 22:38
 */
@RequiredArgsConstructor
@Api(tags = "众筹项目")
@RestController
@RequestMapping("project")
public class ProjectController {

    private final ProjectService projectService;

    @ApiOperation("首页项目")
    @AnonymousAccess
    @GetMapping("front/index")
    public CommonResult<IndexProjectListVO> getIndexShowProject() {
        return CommonResult.success(projectService.getIndexShowProject());
    }

    @ApiOperation("项目详情")
    @AnonymousAccess
    @GetMapping("front/detail")
    public CommonResult<ProjectDetailVO> getIndexProjectDetail(Integer id) {
        return CommonResult.success(projectService.getIndexProjectDetail(id));
    }

    @ApiOperation("项目分页")
    @AnonymousAccess
    @PostMapping("front/list")
    public CommonResult<PageVO<ProjectVO>> getProjectList(@RequestBody ProjectPageReqVO reqVO) {
        return CommonResult.success(projectService.getProjectPageList(reqVO));
    }

    @ApiOperation("我发起的项目")
    @PostMapping("front/myself")
    public CommonResult<PageVO<ProjectVO>> getMyselfProjectList(@RequestBody ProjectPageReqVO reqVO) {
        return CommonResult.success(projectService.getMyselfProjectList(reqVO));
    }

    @ApiOperation("发起项目")
    @PostMapping("front/lunch")
    public CommonResult<Void> lunchProject(@Validated @RequestBody LunchProjectVO reqVO) {
        return CommonResult.success(projectService.lunchProject(reqVO));
    }

/*    @ApiOperation("项目发起页")
    @AnonymousAccess
    @GetMapping("front/page/private/lunch")
    public void goProjectLunchPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/pages/front/private/faqi.html").forward(request, response);
    }*/

    @ApiOperation("管理员项目分页")
    @PostMapping("admin/page/list")
    public CommonResult<PageVO<ProjectVO>> getAdminPageList(@RequestBody ProjectPageReqVO reqVO) {
        return CommonResult.success(projectService.getAdminPageList(reqVO));
    }

}
