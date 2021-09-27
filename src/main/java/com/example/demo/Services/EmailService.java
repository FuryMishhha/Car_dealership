package com.example.demo.Services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
/**
 * Класс EmailService, который отвечает за бизнес-логику работы с почтой.
 */
@Service
@Transactional
public class EmailService {

    /**
     * Метод отправки сообщениия на почту
     * @param message сообщение
     * @param email почта пользователя
     * @throws AddressException
     * @throws MessagingException
     * @throws IOException
     */
    @Async
    public void sendmail(String message, String email) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cardealershipcredo@gmail.com", "lolkekasas");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("cardealershipcredo@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject("Создание заказа");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Ваш заказ {" + message + '}' + " успешно создан", "text/html; charset=UTF-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}