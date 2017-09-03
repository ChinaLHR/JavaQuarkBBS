package com.quark.rest.controller;

import com.quark.common.dto.UploadResult;
import com.quark.common.exception.ServiceProcessException;
import com.quark.rest.service.UserService;
import com.quark.rest.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private UserService userService;

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

    @ApiOperation("用户头像上传接口")
    @PostMapping("/usericon/{token}")
    public UploadResult iconUpload(@PathVariable("token") String token,@RequestParam("file") MultipartFile file){
        UploadResult result = null;
        if (!file.isEmpty()) {
            try {
                String icon = FileUtils.uploadFile(file);
                userService.updataUserIcon(token,icon);
                return new UploadResult(0, new UploadResult.Data(icon));

            } catch (IOException e) {
                e.printStackTrace();
                return new UploadResult(1,"上传图片失败");
            }catch (ServiceProcessException e1){
                e1.printStackTrace();
                return new UploadResult(1,e1.getMessage());
            }
        }
        return new UploadResult(1,"文件不存在");
    }



}
