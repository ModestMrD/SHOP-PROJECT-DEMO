package com.dq.service;

/**
 * @author DuQian
 * @Date 2019/3/19
 */
public interface IEmailService {
    void sendSimpleMail(String to,String subject,String content);

    void sendHtmlMail(String to,String subject,String content);

    void sendAttachmentMail(String to,String subject,String content,String filePath);
}
