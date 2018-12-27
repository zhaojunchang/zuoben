package com.zuoben.mq.rpc;

import com.zuoben.mq.service.MailService;
import com.zuoben.vo.email.Message;
import org.thymeleaf.TemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author zuoben
 * @create 2017-06-21 8:15
 */
@RestController
@RequestMapping("api/mq")
public class MailRest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 发送普通文本邮件
     * @param to  发送者
     * @param subject  接受者
     * @param content  内容
     * @return
     */
    @PostMapping("/send/simple")
    public void simpleMail(String[] to, String subject, String content) {
        mailService.sendSimpleMail(to, subject, content);
    }

    /**
     * 发送HTML邮件
     * @param to  发送者
     * @param subject  接受者
     * @param content  内容
     * @return
     */
    @PostMapping("/send/html")
    public void htmlMail(String[] to, String subject, String content) {
        mailService.sendHtmlMail(to, subject, content);
    }

    /**
     * 发送验证码HTML邮件
     * @param to  发送者
     * @param code  验证码
     * @return
     */
    @PostMapping("/send/code")
    public void codeMail(String to, String code) {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>【ZUOBEN】验证码：" + code + "，五分钟内有效.</h3>\n" +
                "</body>\n" +
                "</html>";

        mailService.sendHtmlMail(new String[]{to}, "【ZUOBEN】验证码", content);
    }

    /**
     * 发送带附件的邮件
     * @param to  发送者
     * @param subject  接受者
     * @param content  内容
     * @param filePath  附件地址
     * @return
     */
    @PostMapping("/send/attachments")
    public void attachmentsMail(String[] to, String subject, String content, String filePath) {
        mailService.sendAttachmentsMail(to, subject, content, filePath);
    }

    /**
     * 发送正文中有静态资源（图片）的邮件
     * @param to  发送者
     * @param subject  接受者
     * @param content  内容
     * @param imgPath  图片地址
     * @return
     */
    @PostMapping("/send/inlineResourc")
    public void inlineResourceMail(String[] to, String subject, String content, String imgPath, String rscId) {
        mailService.sendInlineResourceMail(to, subject, content, imgPath, rscId);
    }

    /**
     * 发送模板邮件
     * @param to  发送者
     * @param subject  接受者
     * @return
     */
    @PostMapping("/send/template")
    public void templateMail(String[] to, String subject) {
        Message message = new Message();

        message.setMessageCode("MissingParameter");
        message.setMessageStatus("Failed");
        message.setCause("缺少参数,请确认");

        mailService.sendTemplateMail(to, subject, "emailTemplate.ftl", message);
    }
}
