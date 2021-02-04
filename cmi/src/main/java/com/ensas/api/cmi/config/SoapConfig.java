package com.ensas.api.cmi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

@Configuration
public class SoapConfig {

    @Bean
    public SimpleJaxWsServiceExporter getJWS() {
        SimpleJaxWsServiceExporter exporter=new SimpleJaxWsServiceExporter();
        exporter.setBaseAddress("http://0.0.0.0:8585/");
        return exporter;
    }
}
