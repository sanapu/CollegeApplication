package com.college.application;

import com.college.application.service.ApplicationService;
import com.college.application.service.ApplicationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class AppConfig {

    @Bean
    public ApplicationServiceImpl getApplicationService(){
        return new ApplicationServiceImpl();
    }
}
