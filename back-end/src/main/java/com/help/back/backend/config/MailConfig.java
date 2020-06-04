package com.help.back.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@PropertySources({
        @PropertySource( value = "classpath:properties/application_auth.properties", ignoreResourceNotFound = true)
})
public class MailConfig {
    Properties pt=new Properties();

    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.smtp.socketFactory.port}")
    private int socketPort;
    @Value("${spring.mail.smtp.auth}")
    private boolean auth;
    @Value("${spring.mail.smtp.starttls.enable}")
    private boolean starttls;
    @Value("${spring.mail.smtp.starttls.required}")
    private boolean startlls_required;
    @Value("${spring.mail.smtp.socketFactory.fallback}")
    private boolean fallback;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;



    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setPort(port);

        pt.put("mail.smtp.socketFactory.port", socketPort); pt.put("mail.smtp.auth", auth);
        pt.put("mail.smtp.starttls.enable", starttls); pt.put("mail.smtp.starttls.required", startlls_required);
        pt.put("mail.smtp.socketFactory.fallback",fallback);
        pt.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailSender.setJavaMailProperties(pt);
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }
}


