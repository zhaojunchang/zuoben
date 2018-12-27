package com.zuoben.util;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.zuoben.dto.FileResp;
import com.zuoben.util.log.LogUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;

/**
 * 七牛云存储
 *
 * @author zhangzhuo
 */
public class QiniuUploadUtil {
    private static final String ak = "8b68aJRDUDzI1HArUC4paH1sV0g3Vm6W3s4RbJeZ";
    private static final String sk = "npBqglBlMi3Q3vSbSmXaINJ2txtWXBMjROIxP8Kg";
    /**
     * 在七牛配置的自定义前缀
     */
    private static final String qiniuCustomDomain = "http://upload.guotaowang.cn/";
    /**
     * 文件桶名称
     */
    private static final String bucket = "zuoben";

    /**
     * @return
     */
    public static String gettoken() {
        Auth auth = Auth.create(ak, sk);
        return auth.uploadToken(bucket);
    }

    public static Auth getAuth() {
        return Auth.create(ak, sk);
    }

    /**
     * 上传文件
     *
     * @param fileName    带文件类型后缀的文件名,若不传则为文件的hash码
     *                    如:key="123.txt",则文件url为http://upload.huilvyun.com/123.txt
     * @param uploadBytes 文件的字节数组
     * @return
     */
    public static FileResp uploadFile(String fileName, byte[] uploadBytes) {
        LogUtil.info("七牛上传文件请求参数:{},{}", fileName, uploadBytes);
        DefaultPutRet putRet = new DefaultPutRet();
        FileResp fileResp = new FileResp();
        try {
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone1());
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            //...生成上传凭证，然后准备上传
            String upToken = QiniuUploadUtil.gettoken();
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
            Response response = uploadManager.put(byteInputStream, fileName, upToken, null, null);
            //解析上传成功的结果
            putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            LogUtil.info("七牛上传文件返回结果:{}", JSON.toJSONString(putRet));
        } catch (Exception e) {
            LogUtil.error("fileName:{},七牛上传图片出错,开始本地上传:{}", fileName, e.getMessage());
        }
        if (StringUtils.isNotBlank(putRet.key)) {
            fileResp.setFileName(putRet.key);
            fileResp.setFileUrl(qiniuCustomDomain + putRet.key);
        }
        return fileResp;
    }

    /**
     * 从七牛删除文件
     *
     * @param key 七牛为文件生成的key,即url后缀.
     *            如:http://upload.huilvyun.com/123.txt,key即为123.txt
     * @throws QiniuException
     */
    public static void deleteFile(String key) throws QiniuException {
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = QiniuUploadUtil.getAuth();
        BucketManager bucketManager = new BucketManager(auth, cfg);
        bucketManager.delete(bucket, key);
    }
}
