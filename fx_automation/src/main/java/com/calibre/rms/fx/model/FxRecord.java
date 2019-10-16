package com.calibre.rms.fx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FxRecord {
    @JsonProperty("code")
    String pair;

    @JsonProperty("close")
    BigDecimal value;

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FxRecord{" +
                "pair='" + pair + '\'' +
                ", value=" + value +
                '}';
    }
}
