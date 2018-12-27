package com.zuoben.sys.controller;

import com.zuoben.sys.service.UploadService;
import com.zuoben.util.resultUtils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author zuoben
 */
@RestController
@RequestMapping("/file")
@Api(value = "API - UploadController", description = "文件相关")
public class UploadController {
    Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Resource
    private UploadService uploadService;

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping("upload")
    public JsonResult fileUpload(@RequestParam("file") MultipartFile file) {
        return uploadService.upload(file);
    }

    @ApiOperation(value = "读取图片")
    @GetMapping("showPhotos/{fileName}")
    public ResponseEntity showPhotos(@PathVariable String fileName) {
        return uploadService.showPhotos(fileName);
    }
}
