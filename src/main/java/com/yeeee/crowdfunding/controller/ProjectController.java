package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.annotation.AnonymousAccess;
import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.IndexProjectListVO;
import com.yeeee.crowdfunding.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
