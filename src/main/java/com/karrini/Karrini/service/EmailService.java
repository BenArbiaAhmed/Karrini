package com.karrini.Karrini.service;

import com.karrini.Karrini.dto.MailBody;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendSimpleMessage(MailBody mailbody){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailbody.to());
        message.setFrom("hmedbenarbia15@gmail.com");
        message.setSubject(mailbody.subject());
        message.setText(mailbody.text());
        javaMailSender.send(message);
    }
}
