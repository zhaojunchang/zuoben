package com.zuoben.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.zuoben.dto.FileResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brook on 2015/12/23.
 */
public final class QrcodeUtil {
    private static final Logger log = LoggerFactory.getLogger(QrcodeUtil.class);
    private static final RestTemplate template = new RestTemplate();
//    private static final String sinaBaseUrl = "http://api.t.sina.com.cn/short_url/shorten.json?source=2815391962";
    /**
     * 本地上传路径
     */
//    private static final String path = "/data/image/";
    /**
     * 本地图片url地址
     */
//    private static final String picUrl = "uploadpic.zhiyousx.com/";

    public static ByteArrayOutputStream generate(String content,
                                                 int width,
                                                 int height,
                                                 String format)
            throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage buffer = MatrixToImageWriter.toBufferedImage(bitMatrix);// 输出图像
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(buffer, format, out);
        return out;
    }

    /**
     * @param content 二维码指定内容
     * @param width   二维码宽度
     * @param height  二维码高度
     * @return
     */
//    public static String generateQrcode(int content, int width, QRSizeEnum height) {
//        String fileName = RandomUtil.getRandomNum(6) + ".png";
//        if (width <= 0) {
//            width = 480; // 图像宽度
//        }
//        if (height <= 0) {
//            height = 480; // 图像高度
//        }
//        String format = "png";// 图像类型
//        try {
//            byte[] data = generate(content, width, height, format).toByteArray();
//            log.info("----------二维码字节长度：" + data.length + "-----------");
//            //上传,返回url
//            FileResp fileResp = QiniuUploadUtil.uploadFile(fileName, data);
//            return fileResp.getFileUrl();
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//        }
//        return "";
//    }

    /**
     * 生成二维码并自动上传至七牛,返回链接地址
     *
     * @param fileName 文件名,若不传则随机生成
     * @param content  二维码内容
     * @param type     二维码大小
     * @return
     */
    public static String generateQrcode(String fileName, String content, QRSizeEnum type) {
        log.info("生成二维码请求参数:fileName:{},content:{},type:{}", fileName, content, type.name());
        if (fileName != null) {
            fileName = fileName + ".png";
        } else {
            fileName = RandomUtil.getRandomNum(10) + ".png";
        }
        Integer size = type.size;
        final String format = "png";// 图像类型
        byte[] data;
        try {
            data = generate(content, size, size, format).toByteArray();
            log.info("二维码名称:" + fileName + "----------二维码字节长度：" + data.length + "-----------");
        } catch (Exception ex) {
            log.error("生成二维码出错:{}", ex.getMessage());
            return null;
        }
        //上传,返回url
        FileResp fileResp = QiniuUploadUtil.uploadFile(fileName, data);
        return fileResp.getFileUrl();
    }

    /**
     * 生成二维码采用新浪短链接
     */
//    public static String generateQrcodeWithShortUrl(String fileName, String content, QRSizeEnum type) {
//        try {
//            String longUrl = generateQrcode(fileName, content, type);
//            String sinaUrl = sinaBaseUrl + "&url_long=" + longUrl;
//            JSONArray resp = template.getForObject(sinaUrl, JSONArray.class);
//            if (resp != null && resp.get(0) != null && resp.getJSONObject(0) != null) {
//                return resp.getJSONObject(0).getString("url_short");
//            }
//        } catch (RestClientException e) {
//            log.error(e.getMessage());
//        }
//        return "";
//    }

    /**
     * 解码二维码信息
     *
     * @param b
     * @return
     */
    public static String qrcodeDecode(byte[] b) {
        ByteArrayInputStream in = new ByteArrayInputStream(b);
        BufferedImage image;
        String string = null;
        try {
            image = ImageIO.read(in);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
            string = result.getText();
            log.info(string);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 二维码大小
     * Created by gaoxi on 2016.05.22 0022.
     */
    public enum QRSizeEnum {
        SMALLEST(1, 200, "8CM"), //
        NORMAL(2, 730, "15CM"), //
        BIG(3, 1455, "30CM"), //
        BIGGEST(4, 2410, "50CM"), //

        ;
        int id;
        int size;
        String text;

        QRSizeEnum(int id, int size, String text) {
            this.id = id;
            this.size = size;
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
