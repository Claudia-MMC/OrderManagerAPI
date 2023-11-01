package com.exercise.ordermanager.service;

public interface EmailService {

    void sendOrderNotification(String to, String subject, String text);
}
