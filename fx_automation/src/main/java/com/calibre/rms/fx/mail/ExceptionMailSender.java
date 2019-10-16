package com.calibre.rms.fx.mail;

import com.calibre.rms.fx.config.Configuration;
import com.calibre.rms.fx.model.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class ExceptionMailSender {

    final static Logger logger = LoggerFactory.getLogger(ExceptionMailSender.class);

    @Autowired
    private Configuration configuration;


    @Autowired
    private EmailService emailService;

    public void sendMail(Exception e){
        Mail mail = new Mail();
        mail.setTo(configuration.getIderrors());
        mail.setFrom(configuration.getIdfrom());
        mail.setSubject("Exception | FX Data Job");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);

        mail.setContent("An exception has been encountered while running FX Data job : " +
                System.lineSeparator() +
                sw.toString());

        try {
            emailService.sendSimpleMessage(mail);
        } catch (MessagingException ex) {
            logger.error("Exception while sending mail " ,ex);
        }
    }
}
