package com.ensas.api.cmi.sms;

import com.ensas.api.cmi.config.CustomProperties;
import com.ensas.api.cmi.config.TwilioInitializer;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSender implements SmsSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    @Autowired
    private CustomProperties props;

    @Override
    public void sendSms(SmsRequest smsRequest) {
        MessageCreator creator = Message.creator(
                new PhoneNumber("whatsapp:"+smsRequest.getPhoneNumber()),
                new PhoneNumber("whatsapp:"+props.getTrialNumber()),
                smsRequest.getMessage()
        );
        creator.create();
        LOGGER.info("Sens sms {} ",smsRequest);
    }
}
