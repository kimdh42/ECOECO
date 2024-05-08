package com.recycle.ecoeco.membership.service.user;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    public boolean sendTemporaryPasswordEmail(String userEmail, String temporaryPassword) {

        final String username = "rlaeogks004@gmail.com";
        final String password = "nscdznlymjeidess";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("ECOECO 임시 비밀번호 발송 입니다.");;
            message.setText("회원님의 임시 비밀번호는 [ " + temporaryPassword + " ] 입니다.");

            Transport.send(message);
            System.out.println("이메일 전송 완료");
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
