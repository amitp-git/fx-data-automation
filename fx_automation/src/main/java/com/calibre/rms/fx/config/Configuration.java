package com.calibre.rms.fx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("fx-automation-service")
public class Configuration {

    private List<String> rates;
    private List<String> header;
    private String filename;
    private List<String> idrates;
    private List<String> iderrors;
    private String idfrom;
    private String scheduledtime;
    private String apiurl;
    private String apitoken;
    private String apiformat;
    private String templocation;

    public List<String> getRates() {
        return rates;
    }

    public void setRates(List<String> rates) {
        this.rates = rates;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<String> getIdrates() {
        return idrates;
    }

    public void setIdrates(List<String> idrates) {
        this.idrates = idrates;
    }

    public List<String> getIderrors() {
        return iderrors;
    }

    public void setIderrors(List<String> iderrors) {
        this.iderrors = iderrors;
    }

    public String getIdfrom() {
        return idfrom;
    }

    public void setIdfrom(String idfrom) {
        this.idfrom = idfrom;
    }

    public String getScheduledtime() {
        return scheduledtime;
    }

    public void setScheduledtime(String scheduledtime) {
        this.scheduledtime = scheduledtime;
    }

    public String getApiurl() {
        return apiurl;
    }

    public void setApiurl(String apiurl) {
        this.apiurl = apiurl;
    }

    public String getApitoken() {
        return apitoken;
    }

    public void setApitoken(String apitoken) {
        this.apitoken = apitoken;
    }

    public String getApiformat() {
        return apiformat;
    }

    public void setApiformat(String apiformat) {
        this.apiformat = apiformat;
    }

    public String getTemplocation() {
        return templocation;
    }

    public void setTemplocation(String templocation) {
        this.templocation = templocation;
    }
}
