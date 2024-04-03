package com.yeeee.crowdfunding.controller;

import com.github.pagehelper.Page;
import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.yeee.memo.integrate.common.websecurity.annotation.AnonymousAccess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 22:38
 */
@RequiredArgsConstructor
@Api(tags = "众筹项目")
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @ApiOperation("首页项目")
    @AnonymousAccess
    @GetMapping("front/project/index")
    public CommonResult<IndexProjectListVO> getIndexShowProject() {
        return CommonResult.success(projectService.getIndexShowProject());
    }

    @ApiOperation("项目详情")
    @AnonymousAccess
    @GetMapping("front/project/detail")
    public CommonResult<ProjectDetailVO> getIndexProjectDetail(Integer id) {
        return CommonResult.success(projectService.getIndexProjectDetail(id));
    }

    @ApiOperation("项目分页")
    @AnonymousAccess
    @PostMapping("front/project/page")
    public CommonResult<PageVO<ProjectVO>> getProjectList(@RequestBody ProjectPageReqVO reqVO) {
        return CommonResult.success(projectService.getProjectPageList(reqVO));
    }

    @ApiOperation("我发起的项目")
    @PostMapping("front/project/myself")
    public CommonResult<PageVO<ProjectVO>> getMyselfProjectList(@RequestBody ProjectPageReqVO reqVO) {
        return CommonResult.success(projectService.getMyselfProjectList(reqVO));
    }

    @ApiOperation("发起项目")
    @PostMapping("front/project/lunch")
    public CommonResult<Void> lunchProject(@Validated @RequestBody LunchProjectVO reqVO) {
        return CommonResult.success(projectService.lunchProject(reqVO));
    }

    @ApiOperation("发起项目")
    @PostMapping("front/project/updateProgress")
    public CommonResult<Void> updateProjectProgress(@Validated @RequestBody ProjectProgressVO projectProgressVO) {
        return CommonResult.success(projectService.updateProjectProgress(projectProgressVO));
    }

    @ApiOperation("项目下单预览页")
    @AnonymousAccess
    @GetMapping("front/project/orderPage")
    public CommonResult<OrderPageVO> frontProjectOrderPageDetail(@RequestParam Integer id) {
        return CommonResult.success(projectService.frontProjectOrderPageDetail(id));
    }

/*    @ApiOperation("项目发起页")
    @AnonymousAccess
    @GetMapping("front/page/private/lunch")
    public void goProjectLunchPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/pages/front/private/faqi.html").forward(request, response);
    }*/

    @ApiOperation("管理员项目分页")
    @PostMapping("admin/project/page")
    public CommonResult<PageVO<ProjectVO>> getAdminPageList(@RequestBody ProjectPageReqVO reqVO) {
        return CommonResult.success(projectService.getAdminPageList(reqVO));
    }

    @ApiOperation("审核-项目详情")
    @GetMapping("admin/project/detail")
    public CommonResult<LunchProjectVO> getAdminProjectDetail(Integer id) {
        return CommonResult.success(projectService.getAdminProjectDetail(id));
    }

    @ApiOperation("审核项目")
    @PostMapping("admin/project/audits")
    public CommonResult<Void> adminProjectAudits(@RequestBody AuditProjectVO auditProjectVO) {
        return CommonResult.success(projectService.adminProjectAudits(auditProjectVO));
    }

}
