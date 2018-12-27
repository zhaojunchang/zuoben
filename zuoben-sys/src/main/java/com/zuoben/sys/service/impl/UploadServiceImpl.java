package com.zuoben.sys.service.impl;

import com.zuoben.dto.FileResp;
import com.zuoben.sys.service.UploadService;
import com.zuoben.util.FileUtils;
import com.zuoben.util.QiniuUploadUtil;
import com.zuoben.util.resultUtils.JsonResult;
import com.zuoben.util.resultUtils.SafeExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.UUID;

import static com.zuoben.util.resultUtils.Guard.argumentNotNull;

/**
 * @author
 * createTime:2018/4/16 0016
 */
@Service
public class UploadServiceImpl implements UploadService {
    Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Value("${web.uploadPath}")
    private String path;

    private final ResourceLoader resourceLoader;

    @Autowired
    public UploadServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public JsonResult upload(MultipartFile file) {
        return SafeExecutor.noTranExecute(() -> {
            String fileName = file.getOriginalFilename();
            argumentNotNull(fileName, "文件名为空");

            FileResp fileResp = null;
            try {
                fileResp = QiniuUploadUtil.uploadFile(UUID.randomUUID().toString(), file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return JsonResult.success(fileResp);
        });
    }

    @Override
    public ResponseEntity showPhotos(String fileName) {
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
