package com.demo.evaluate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "option.cache")
public class CacheConfigProperties {
    private String keyUrl;

    public CacheConfigProperties() {
        
    }

    public String getKeyUrl() {
        return this.keyUrl;
    }

    public void setKeyUrl(String keyUrl) {
        this.keyUrl = keyUrl;
    }
}
