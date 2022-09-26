package com.za.carolsstore.message.service;

import java.time.LocalDateTime;

public interface iSMSService {
    String sendSMS(LocalDateTime dateTime, String toPhoneNumber, String message);
}
