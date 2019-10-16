package com.calibre.rms.fx.input;

import com.calibre.rms.fx.model.FxData;

import java.time.LocalDateTime;

public interface FxDataFetcher {
    FxData getData(LocalDateTime localDateTime);
}
