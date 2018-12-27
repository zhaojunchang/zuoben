package com.zuoben.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Description 本地上传工具类
 * @Author 高翔
 * @Date 2018/11/7 0007 11:15
 */
public class UploadUtil {
    /**
     * @param file     需要上传的文件
     * @param filePath 文件需要存储的路径
     * @param fileName 文件名
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
