package com.yeeee.crowdfunding.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.entity.ProvinceCityDistrict;
import com.yeeee.crowdfunding.service.ProvinceCityDistrictService;
import com.yeeee.crowdfunding.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.yeee.memo.integrate.common.websecurity.annotation.AnonymousAccess;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/1 12:58
 */
@Slf4j
@Api(tags = "通用接口")
@RequiredArgsConstructor
@RestController
@RequestMapping("general")
public class CommonController {

    private final ProvinceCityDistrictService provinceCityDistrictService;

    private final UserService userService;

    @Value("${local.upload.location}")
    private String uploadPath;

    @ApiOperation("获取地区")
    @AnonymousAccess
    @GetMapping("region")
    public CommonResult<List<ProvinceCityDistrict>> getRegion(Integer pid) {
        return CommonResult.success(provinceCityDistrictService.getList(pid));
    }

    @ApiOperation("退出登录")
    @GetMapping(value = "/logout")
    public CommonResult<Void> logout() {
        return CommonResult.success(userService.logout());
    }

    @ApiOperation("上传")
    @AnonymousAccess
    @PostMapping("upload")
    public CommonResult<String> upload(@RequestPart List<MultipartFile> file, @RequestParam String path) throws FileNotFoundException {
        List<String> paths = Lists.newArrayList();
        file.forEach(f -> {
            String dirPath;
            try (OutputStream out = FileUtil.getOutputStream(FileUtil.file(uploadPath + (dirPath = ("upload/" + path + "/" + RandomUtil.randomNumbers(5) + f.getOriginalFilename()))))) {
                IoUtil.copy(f.getInputStream(), out);
                paths.add("/" + dirPath.replace("\\", "/"));
            } catch (IOException e) {
                log.error("upload err", e);
            }
        });
        return CommonResult.success(String.join(";", paths));
    }

}
