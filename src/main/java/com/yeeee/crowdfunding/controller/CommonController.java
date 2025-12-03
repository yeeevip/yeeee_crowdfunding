package com.yeeee.crowdfunding.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.biz.CommonBiz;
import com.yeeee.crowdfunding.model.entity.ProvinceCityDistrict;
import com.yeeee.crowdfunding.service.ProvinceCityDistrictService;
import com.yeeee.crowdfunding.service.UserService;
import com.yeeee.crowdfunding.utils.PathUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.yeee.memo.integrate.base.websecurityoauth2.annotation.AnonymousAccess;

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
@RequestMapping("/general")
public class CommonController {

    private final ProvinceCityDistrictService provinceCityDistrictService;
    private final UserService userService;
    private final CommonBiz commonBiz;

    @Value("${local.upload.location}")
    private String uploadPath;

    @ApiOperation("获取地区")
    @AnonymousAccess
    @GetMapping("/region")
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
    @PostMapping("/upload")
    public CommonResult<String> upload(@RequestPart List<MultipartFile> file,
                                       @RequestParam String path) throws FileNotFoundException {

        // 清理 path，避免前后带 "/"
        path = path.replaceAll("^/+", "").replaceAll("/+$", "");

        List<String> paths = Lists.newArrayList();

        String finalPath = path;
        file.forEach(f -> {
            try {
                // 处理原始文件名和后缀
                String original = f.getOriginalFilename();
                String ext = "";
                if (original != null && original.contains(".")) {
                    ext = original.substring(original.lastIndexOf(".")); // 包含 .
                }

                // 拼接新文件名
                String filename = RandomUtil.randomNumbers(5) + ext;

                // 最终存储目录
                String dirPath = "upload/" + finalPath + "/" + filename;

                // 生成绝对路径（你的工具保持不变）
                File target = FileUtil.file(PathUtils.getClassLoadRootPath() + "/" + dirPath);

                // 创建目录并写入
                FileUtil.mkParentDirs(target);
                try (OutputStream out = FileUtil.getOutputStream(target)) {
                    IoUtil.copy(f.getInputStream(), out);
                }

                // 返回给前端使用的路径，保持斜杠统一
                paths.add("/" + dirPath.replace("\\", "/"));

            } catch (Exception e) {
                log.error("upload err", e);
            }
        });

        return CommonResult.success(String.join(";", paths));
    }

    @ApiOperation("获取验证码")
    @GetMapping(value = "/check-code")
    public void getCheckCode() throws IOException {
        commonBiz.getCheckCode();
    }

}
