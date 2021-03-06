package com.ensas.api.cmi.config;

import com.ensas.api.cmi.sec.UserDetailsServiceImpl;
import com.ensas.api.cmi.services.AccountService;
import com.ensas.api.cmi.services.AccountServiceImpl;
import com.ensas.api.cmi.sms.SmsSender;
import com.ensas.api.cmi.sms.TwilioSmsSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AppConfig {

    @Bean
    public AccountService accountService() {
        return new AccountServiceImpl();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public SmsSender smsSender(){
        return new TwilioSmsSender();
    }
}
