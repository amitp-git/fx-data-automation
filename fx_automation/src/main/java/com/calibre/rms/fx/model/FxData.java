package com.calibre.rms.fx.model;

import java.time.LocalDateTime;
import java.util.List;

public class FxData {

    private LocalDateTime dateTime = null;
    private List<FxRecord> fxRecordList;

    public FxData(LocalDateTime dateTime,List<FxRecord> fxRecordList){
        this.dateTime = dateTime;
        this.fxRecordList = fxRecordList;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<FxRecord> getFxRecordList() {
        return fxRecordList;
    }

    public void setFxRecordList(List<FxRecord> fxRecordList) {
        this.fxRecordList = fxRecordList;
    }

    @Override
    public String toString() {
        return "FxData{" +
                "dateTime=" + dateTime +
                ", fxRecordList=" + fxRecordList +
                '}';
    }
}
