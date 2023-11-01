package com.exercise.ordermanager.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LogManager.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderNotification(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
            logger.info("Email sent to " + to);
        } catch (Exception e) {
            logger.error("Error sending email: " + e.getMessage(), e);
            throw e;
        }

    }
}
