package edu.sharif.selab.services;

import edu.sharif.selab.models.EmailMessage;

public interface IEmailMessageService {
    void sendEmailMessage(EmailMessage emailMessage);
} 