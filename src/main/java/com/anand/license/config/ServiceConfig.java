package com.anand.license.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "example")
@Component
public class ServiceConfig {

    private String property;


    public String getProperty() {
        return property;
    }

    public void setProperty(String property){
        this.property=property;
    }
}