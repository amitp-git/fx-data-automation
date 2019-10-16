package com.calibre.rms.fx.input;

import com.calibre.rms.fx.config.Configuration;
import com.calibre.rms.fx.model.FxData;
import com.calibre.rms.fx.model.FxRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class APIFxDataFetcher implements FxDataFetcher {

    @Autowired
    private Configuration configuration;

    @Autowired
    private FxRecordFetcher fxRecordFetcher;


    @Override
    public FxData getData(LocalDateTime localDateTime) {

        List<FxRecord> fxRecords = new ArrayList<>();
        for(String pair:configuration.getRates()){
            String url = getAPIUrl(pair);
            FxRecord fxRecord = fxRecordFetcher.getData(url);
            fxRecords.add(fxRecord);
        }

        return new FxData(localDateTime,fxRecords);
    }

    private String getAPIUrl(String pair){
        String url = configuration.getApiurl() + pair + ".FOREX?" + "api_token="
                + configuration.getApitoken() + "&fmt=" + configuration.getApiformat();
        return url;
    }
}
