package edu.sharif.selab.services;

import edu.sharif.selab.models.SmsMessage;

public interface ISmsMessageService {
    void sendSmsMessage(SmsMessage smsMessage);
} 