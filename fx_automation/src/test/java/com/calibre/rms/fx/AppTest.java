/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.calibre.rms.fx;

import com.calibre.rms.fx.config.Configuration;
import com.calibre.rms.fx.input.APIFxDataFetcher;
import com.calibre.rms.fx.input.FxDataFetcher;
import com.calibre.rms.fx.input.FxRecordFetcher;
import com.calibre.rms.fx.model.FxData;
import com.calibre.rms.fx.model.FxRecord;
import com.calibre.rms.fx.scheduler.Scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {

    @MockBean
    private Configuration configuration;

    @MockBean
    RestTemplate restTemplate;

    @MockBean
    public Scheduler scheduler;

    @Autowired
    private FxDataFetcher fxDataFetcher;

    @Autowired
    private FxRecordFetcher fxRecordFetcher;

    @Before
    public void setup() throws Exception {

        FxRecord fxRecord = new FxRecord();
        fxRecord.setPair("AUDEUR.FOREX");
        fxRecord.setValue(BigDecimal.valueOf(0.61));

        List<String> rates = new ArrayList<>();
        rates.add("AUDEUR");

        Mockito
          .when(restTemplate.getForObject(anyString(),any()))
          .thenReturn(fxRecord);

        Mockito.when(configuration.getRates())
                .thenReturn(rates);

        Mockito.when(configuration.getApiurl())
                .thenReturn("");

        Mockito.when(configuration.getApitoken())
                .thenReturn("");

        Mockito.when(configuration.getApiformat())
                .thenReturn("");

    }

    @Test
    public void testFxDataFetcher() throws Exception {

        LocalDateTime localDateTime = LocalDateTime.now();
        FxData fxData =  fxDataFetcher.getData(localDateTime);
        Assert.assertEquals(fxData.getDateTime(), localDateTime);
        Assert.assertEquals(fxData.getFxRecordList().size(),1);
        Assert.assertEquals(fxData.getFxRecordList().get(0).getPair(),"AUDEUR");
        Assert.assertEquals(fxData.getFxRecordList().get(0).getValue(),BigDecimal.valueOf(0.61));

    }

}