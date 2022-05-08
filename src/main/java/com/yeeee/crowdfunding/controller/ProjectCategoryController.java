package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.ProjectCategoryVO;
import com.yeeee.crowdfunding.model.vo.ProjectPageReqVO;
import com.yeeee.crowdfunding.service.ProjectCategoryService;
import com.yeeee.crowdfunding.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/8 16:38
 */
@Api(tags = "项目类别")
@RequiredArgsConstructor
@RestController
public class ProjectCategoryController {

    private final ProjectCategoryService projectCategoryService;

    @ApiOperation("管理员项目类别分页")
    @PostMapping("admin/category/page")
    public CommonResult<PageVO<ProjectCategoryVO>> getAdminProjectCategoryList(@RequestBody ProjectPageReqVO reqVO) {
        return CommonResult.success(projectCategoryService.getAdminProjectCategoryList(reqVO));
    }

}
