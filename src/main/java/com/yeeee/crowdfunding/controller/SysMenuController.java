package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysMenuHasSetVO;
import com.yeeee.crowdfunding.model.vo.SysMenuVO;
import com.yeeee.crowdfunding.service.SysMenuService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/4 17:09
 */
@RequiredArgsConstructor
@RestController
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @GetMapping("admin/menu/listTree")
    public CommonResult<List<SysMenuVO>> getMenuListTree() {
        return CommonResult.success(sysMenuService.getMenuListTree());
    }

    @GetMapping("admin/sys-menu/list")
    public CommonResult<PageVO<SysMenuVO>> getSysMenuListTreenode(String query) {
        return CommonResult.success(sysMenuService.getSysMenuListTreenode(query));
    }

    @GetMapping("admin/menu/getAuthz")
    public CommonResult<Object> getMenuAuthz() {
        return CommonResult.success(sysMenuService.getMenuAuthz());
    }

    @ApiOperation("创建菜单")
    @PostMapping(value = "admin/sys-menu/add")
    public CommonResult<Void> addSysMenu(@Validated(SysMenuVO.Add.class) @RequestBody SysMenuVO editVO) {
        return CommonResult.success(sysMenuService.addSysMenu(editVO));
    }

    @ApiOperation("编辑菜单")
    @PostMapping(value = "admin/sys-menu/upd")
    public CommonResult<Void> editSysMenu(@Validated(SysMenuVO.Edit.class) @RequestBody SysMenuVO editVO) {
        return CommonResult.success(sysMenuService.editSysMenu(editVO));
    }

    @ApiOperation("菜单详情")
    @PostMapping(value = "admin/sys-menu/info")
    public CommonResult<SysMenuVO> sysMenuInfo(@Validated(SysMenuVO.Info.class) @RequestBody SysMenuVO editVO) {
        return CommonResult.success(sysMenuService.sysMenuInfo(editVO));
    }

    @ApiOperation("删除菜单")
    @PostMapping(value = "admin/sys-menu/del")
    public CommonResult<Void> delSysMenu(@Validated(SysMenuVO.Del.class) @RequestBody SysMenuVO editVO) {
        return CommonResult.success(sysMenuService.delSysMenu(editVO));
    }

    @ApiOperation("查询菜单和及已设置的")
    @GetMapping(value = "admin/sys-menu/list-set")
    public CommonResult<SysMenuHasSetVO> sysMenuListAndHasSet(Integer roleId) {
        return CommonResult.success(sysMenuService.sysMenuListAndHasSet(roleId));
    }

}
