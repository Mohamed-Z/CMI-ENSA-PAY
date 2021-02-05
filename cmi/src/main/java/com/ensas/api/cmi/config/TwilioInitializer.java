package com.ensas.api.cmi.config;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);
    private final CustomProperties props;

    @Autowired
    public TwilioInitializer(CustomProperties props) {
        this.props = props;
        Twilio.init(
                props.getAccountSid(),
                props.getAuthToken()
        );
        LOGGER.info("Twilio initialized ... with account sid {} ", props.getAccountSid());
    }
}
