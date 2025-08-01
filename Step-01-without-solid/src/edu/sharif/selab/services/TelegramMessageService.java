package edu.sharif.selab.services;

import edu.sharif.selab.models.EmailMessage;
import edu.sharif.selab.models.SmsMessage;
import edu.sharif.selab.models.TelegramMessage;

public class TelegramMessageService implements MessageService{
    @Override
    public void sendSmsMessage(SmsMessage smsMessage) {
        //Empty Body!
    }

    @Override
    public void sendEmailMessage(EmailMessage emailMessage) {
        //Empty Body!
    }

    public void sendTelegramMessage(TelegramMessage telegramMessage) {
        if(validateTelegramId(telegramMessage.getSourceId()) && validateTelegramId(telegramMessage.getTargetId())){
            System.out.println("Sending a Telegram message from " + telegramMessage.getSourceId() + " to " + telegramMessage.getTargetId() + " with content : " + telegramMessage.getContent());
        }else{
            throw new IllegalArgumentException("Telegram ID is Not Correct!");
        }
    }

    private boolean validateTelegramId(String telegramId) {
        if (telegramId == null || telegramId.trim().isEmpty()) {
            return false;
        }

        telegramId = telegramId.trim();

        // Case 1: Telegram username (starts with @, followed by at least 5 alphanumeric characters or underscores)
        if (telegramId.startsWith("@")) {
            String username = telegramId.substring(1);
            if (username.length() >= 5 && username.matches("[a-zA-Z0-9_]+")) {
                return true;
            }
        }

        // Case 2: Telegram user ID (numeric, typically 6-10 digits)
        if (telegramId.matches("\\d{6,10}")) {
            return true;
        }

        // Case 3: Phone number format (11 digits, like SMS validation)
        if (telegramId.length() == 11 && telegramId.matches("\\d{11}")) {
            return true;
        }

        // If none of the formats match, return false
        return false;
    }
} 