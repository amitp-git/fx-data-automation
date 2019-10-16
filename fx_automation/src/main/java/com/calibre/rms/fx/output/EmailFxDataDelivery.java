package com.calibre.rms.fx.output;

import com.calibre.rms.fx.config.Configuration;
import com.calibre.rms.fx.mail.EmailService;
import com.calibre.rms.fx.model.Mail;
import com.calibre.rms.fx.model.FxData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

@Component
public class EmailFxDataDelivery implements FxDataDelivery{

    final static Logger logger = LoggerFactory.getLogger(EmailFxDataDelivery.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    private Configuration configuration;

    @Override
    public void deliver(FxData fxData) {
        logger.info("Sending mail with fx data for time " + fxData.getDateTime());

        Mail mail = new Mail();
        mail.setTo(configuration.getIdrates());
        mail.setFrom(configuration.getIdfrom());
        mail.setContent("The fx data extract is attached in the mail below.");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
            String formattedDateTime = fxData.getDateTime().format(formatter);
            String fileName = configuration.getFilename() + formattedDateTime + ".csv";
            mail.setSubject("Fx Data | " + formattedDateTime);

            File file = new File(fileName);
            mail.setPath(file.getPath());
            mail.setFileName(fileName);
            FileWriter csvWriter = new FileWriter(file);

            final StringJoiner joiner = new StringJoiner(",");
            configuration.getHeader().stream().forEach(joiner::add);
            csvWriter.append(joiner.toString()+System.lineSeparator() );

            fxData.getFxRecordList().stream()
                    .forEach(fx-> {
                        try {
                            csvWriter.append(fx.getPair() + "," + fx.getValue()
                            + System.lineSeparator());
                        } catch (IOException e) {
                            logger.error("Error while creating attachment" ,e);
                        }
                    });

            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            logger.error("Error while creating attachment" ,e);
            throw new RuntimeException(e);
        }

        try {
            emailService.sendSimpleMessage(mail);
        } catch (MessagingException e) {
            logger.error("Error while sending mail" ,e);
            throw new RuntimeException(e);
        }
    }
}
