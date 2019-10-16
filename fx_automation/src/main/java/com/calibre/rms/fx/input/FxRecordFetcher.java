package com.calibre.rms.fx.input;

import com.calibre.rms.fx.model.FxRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FxRecordFetcher {

    final static Logger logger = LoggerFactory.getLogger(FxRecordFetcher.class);

    @Autowired
    RestTemplate restTemplate;


    public FxRecord getData(String apiUrl){
        logger.info("Going to fetch fx record from api " + apiUrl);
        FxRecord fxRecord = restTemplate.getForObject(apiUrl, FxRecord.class);
        fxRecord.setPair(fxRecord.getPair().replace(".FOREX",""));
        logger.info("Fx record fetched from api " + fxRecord.toString());
        return fxRecord;
    };

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
