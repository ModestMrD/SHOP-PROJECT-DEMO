package com.dq.shopemailservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dq.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author DuQian
 * @Date 2019/3/19
 */
@Component
public class EmailServiceImpl implements IEmailService{

    @Autowired
    private JavaMailSender sender;

    @Value("${mail.from}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        sender.send(message);
        System.out.println("发送信息成功");
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            sender.send(message);
            System.out.println("发送信息成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendAttachmentMail(String to, String subject, String content, String filePath) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            FileSystemResource resource = new FileSystemResource(filePath);
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName,resource);
            sender.send(message);
            System.out.println("发送信息成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
