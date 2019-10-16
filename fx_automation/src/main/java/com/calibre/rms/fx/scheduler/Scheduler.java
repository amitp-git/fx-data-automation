package com.calibre.rms.fx.scheduler;


import com.calibre.rms.fx.input.FxDataFetcher;
import com.calibre.rms.fx.mail.ExceptionMailSender;
import com.calibre.rms.fx.model.FxData;
import com.calibre.rms.fx.output.FxDataDelivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Scheduler {

    final static Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private FxDataFetcher fxDataFetcher;

    @Autowired
    private List<FxDataDelivery> fxDataDeliveryList;

    @Autowired
    private ExceptionMailSender exceptionMailSender;

    @Scheduled(cron="${fx-automation-service.scheduledtime}")
    public void cronJobSch() {

        try{
            logger.info("Fx Data Job to be executed");
            LocalDateTime localDateTime = LocalDateTime.now();

            FxData fxData = fxDataFetcher.getData(localDateTime);
            logger.info("Fx Data Job executed at "+ localDateTime + " data " + fxData);

            fxDataDeliveryList.stream().forEach(fxDelivery -> fxDelivery.deliver(fxData));
        }catch (Exception t){
            logger.error("Exception while executing Fx Data Job ",t);
            exceptionMailSender.sendMail(t);
        }

    }
}