package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysDeptVO;
import com.yeeee.crowdfunding.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/28 18:12
 */
@Api(tags = "组织机构管理")
@RequiredArgsConstructor
@RestController
public class SysDeptController {

    private final SysDeptService sysDeptService;

    @ApiOperation("机构分页")
    @GetMapping(value = "admin/sys-dept/page")
    public CommonResult<PageVO<SysDeptVO>> sysDeptPageList(String query) {
        return CommonResult.success(sysDeptService.sysDeptPageList(query));
    }

    @ApiOperation("机构是否存在")
    @GetMapping(value = "admin/sys-dept/exist")
    public CommonResult<Boolean> sysDeptExist(String query) {
        return CommonResult.success(sysDeptService.sysDeptExist(query));
    }

    @ApiOperation("创建机构")
    @PostMapping(value = "admin/sys-dept/add")
    public CommonResult<Void> addSysDept(@Validated(SysDeptVO.Add.class) @RequestBody SysDeptVO editVO) {
        return CommonResult.success(sysDeptService.addSysDept(editVO));
    }

    @ApiOperation("编辑机构")
    @PostMapping(value = "admin/sys-dept/upd")
    public CommonResult<Void> editSysDept(@Validated(SysDeptVO.Edit.class) @RequestBody SysDeptVO editVO) {
        return CommonResult.success(sysDeptService.editSysDept(editVO));
    }

    @ApiOperation("机构详情")
    @PostMapping(value = "admin/sys-dept/info")
    public CommonResult<SysDeptVO> sysDeptInfo(@Validated(SysDeptVO.Info.class) @RequestBody SysDeptVO editVO) {
        return CommonResult.success(sysDeptService.sysDeptInfo(editVO));
    }

    @ApiOperation("删除机构")
    @PostMapping(value = "admin/sys-dept/del")
    public CommonResult<Void> delSysDept(@Validated(SysDeptVO.Del.class) @RequestBody SysDeptVO editVO) {
        return CommonResult.success(sysDeptService.delSysDept(editVO));
    }

}
