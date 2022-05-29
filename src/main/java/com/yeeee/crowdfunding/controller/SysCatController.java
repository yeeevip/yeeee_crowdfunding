package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysCatVO;
import com.yeeee.crowdfunding.service.SysCatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/28 18:12
 */
@Api(tags = "数据字典管理")
@RequiredArgsConstructor
@RestController
public class SysCatController {

    private final SysCatService sysCatService;

    @ApiOperation("字典分页")
    @GetMapping(value = "admin/sys-cat/page")
    public CommonResult<PageVO<SysCatVO>> sysCatPageList(String query) {
        return CommonResult.success(sysCatService.sysCatPageList(query));
    }

    @ApiOperation("字典二级树结构")
    @GetMapping(value = "admin/sys-cat/item-tree")
    public CommonResult<PageVO<SysCatVO>> sysItemTreeList(String query) {
        return CommonResult.success(sysCatService.sysItemTreeList(query));
    }

    @ApiOperation("字典是否存在")
    @GetMapping(value = "admin/sys-cat/exist")
    public CommonResult<Boolean> sysCatExist(String query) {
        return CommonResult.success(sysCatService.sysCatExist(query));
    }

    @ApiOperation("创建字典")
    @PostMapping(value = "admin/sys-cat/add")
    public CommonResult<Void> addSysCat(@Validated(SysCatVO.Add.class) @RequestBody SysCatVO editVO) {
        return CommonResult.success(sysCatService.addSysCat(editVO));
    }

    @ApiOperation("编辑字典")
    @PostMapping(value = "admin/sys-cat/upd")
    public CommonResult<Void> editSysCat(@Validated(SysCatVO.Edit.class) @RequestBody SysCatVO editVO) {
        return CommonResult.success(sysCatService.editSysCat(editVO));
    }

    @ApiOperation("字典详情")
    @PostMapping(value = "admin/sys-cat/info")
    public CommonResult<SysCatVO> sysCatInfo(@Validated(SysCatVO.Info.class) @RequestBody SysCatVO editVO) {
        return CommonResult.success(sysCatService.sysCatInfo(editVO));
    }

    @ApiOperation("删除字典")
    @PostMapping(value = "admin/sys-cat/del")
    public CommonResult<Void> delSysCat(@Validated(SysCatVO.Del.class) @RequestBody SysCatVO editVO) {
        return CommonResult.success(sysCatService.delSysCat(editVO));
    }

}
