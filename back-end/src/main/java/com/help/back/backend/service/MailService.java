package com.help.back.backend.service;

import com.help.back.backend.domain.Mail;

public interface MailService {
    public boolean mailSend(Mail mail);
}
