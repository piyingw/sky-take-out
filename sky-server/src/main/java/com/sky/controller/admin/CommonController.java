package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    @PostMapping("/upload")
    public Result< String> upload(MultipartFile file) {
        log.info("文件上传：{}", file);
        //防止文件重名
        try {
        String originalFilename = file.getOriginalFilename();
        String suffix =originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;

            String filepath = aliOssUtil.upload(file.getBytes(),fileName);
            return Result.success(filepath);
        } catch (IOException e) {
            log.info("文件上传失败:{}",e);
            return Result.error("上传失败");
        }
    }
}
