package com.calibre.rms.fx.mail;

import com.calibre.rms.fx.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(Mail mail) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent());
        helper.setTo((mail.getTo().toArray(new String[mail.getTo().size()])));
        helper.setFrom(mail.getFrom());
        helper.setSubject(mail.getSubject());

        if(mail.getPath()!=null){
            FileSystemResource file  = new FileSystemResource(new File(mail.getPath()));
            helper.addAttachment(mail.getFileName(), file);
        }

        emailSender.send(message);

    }

}