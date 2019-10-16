package com.calibre.rms.fx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/config")
    public Configuration getLimitConfig() {
        return configuration;
    }
}