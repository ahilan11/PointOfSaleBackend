package com.za.carolsstore.message.service;

import com.za.carolsstore.message.model.Email;

public interface iEmailService {
    String sendEmailRecipt(String toEmail, String saleID);
    String sendEmailNotifiction(String toEmail);
    String addEmail(Email email);
}
