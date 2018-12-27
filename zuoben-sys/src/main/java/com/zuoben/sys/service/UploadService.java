package com.zuoben.sys.service;

import com.zuoben.util.resultUtils.JsonResult;
import org.apache.http.entity.FileEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author
 * createTime:2018/4/16 0016
 * 文件上传
 */
public interface UploadService {
    /**
     * @param file    文件
     * @return
     */
    JsonResult upload(MultipartFile file);

    /**
     * @param fileName    文件名称
     * @return
     */
    ResponseEntity showPhotos(String fileName);
}
