package edu.sharif.selab.services;

import edu.sharif.selab.models.EmailMessage;
import edu.sharif.selab.models.SmsMessage;
import edu.sharif.selab.models.TelegramMessage;
import edu.sharif.selab.models.Message;

public class MessageServiceFactory {
    
    public static ISmsMessageService createSmsService() {
        return new SmsMessageService();
    }
    
    public static IEmailMessageService createEmailService() {
        return new EmailMessageService();
    }
    
    public static ITelegramMessageService createTelegramService() {
        return new TelegramMessageService();
    }
    
    // Generic method to get appropriate service based on message type
    public static Object getServiceForMessage(Message message) {
        if (message instanceof SmsMessage) {
            return createSmsService();
        } else if (message instanceof EmailMessage) {
            return createEmailService();
        } else if (message instanceof TelegramMessage) {
            return createTelegramService();
        }
        throw new IllegalArgumentException("Unsupported message type: " + message.getClass().getSimpleName());
    }
} 