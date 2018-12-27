package com.zuoben.mq.service;

public interface MailService {
    /**
     * 发送普通邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String[] to, String subject, String content);

    /**
     * 发送html格式邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlMail(String[] to, String subject, String content);

    /**
     * 发送带附件邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    void sendAttachmentsMail(String[] to, String subject, String content, String filePath);

    /**
     * 发送正文中有静态资源（图片）的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    void sendInlineResourceMail(String[] to, String subject, String content, String rscPath, String rscId);

    /**
     * 发送模板邮件
     * @param to
     * @param subject
     * @param params
     * @param templateName
     */
    void sendTemplateMail(String[] to, String subject, String templateName, Object params);
}
