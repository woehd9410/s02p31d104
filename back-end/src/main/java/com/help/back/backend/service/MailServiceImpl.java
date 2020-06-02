package com.help.back.backend.service;

import com.help.back.backend.domain.Mail;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "god123564@naver.com";

    @Override
    public String mailSend(String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        KeyService keyService = new KeyService();
        String key = keyService.getKey(10, false);
        message.setTo(mail);
        message.setFrom(MailServiceImpl.FROM_ADDRESS);
        message.setSubject("Helpromise 비밀번호 변경 관련 인증 메일입니다.");
        message.setText("다음의 인증번호를 확인하시고 비밀번호를 변경해주세요. 인증번호 : " + key);

        try{//예외처리

            mailSender.send(message);
            return key;
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
