package com.ensas.api.cmi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.ensas.api.cmi")
public class CustomProperties {

    private String apiIAMUrL;
    private String accountSid;
    private String authToken;
    private String trialNumber;

}
