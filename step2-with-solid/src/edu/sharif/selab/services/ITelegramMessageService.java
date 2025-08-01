package edu.sharif.selab.services;

import edu.sharif.selab.models.TelegramMessage;

public interface ITelegramMessageService {
    void sendTelegramMessage(TelegramMessage telegramMessage);
} 