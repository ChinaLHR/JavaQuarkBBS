package com.quark.rest.controller;

import com.quark.common.dto.UploadResult;
import com.quark.rest.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author LHR
 * Create By 2017/8/26
 */
@Api(value = "文件上传接口",description = "图片上传")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @ApiOperation("图片上传接口")
    @PostMapping("/image")
    public UploadResult upload(@RequestParam("file") MultipartFile file) {
        UploadResult result = null;
        if (!file.isEmpty()) {
            try {
                String s = FileUtils.uploadFile(file);
              result = new UploadResult(0, new UploadResult.Data(s));
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                result = new UploadResult(1,"上传图片失败");
            }
            return result;
        }
        result = new UploadResult(1,"文件不存在");
        return result;
    }



}
