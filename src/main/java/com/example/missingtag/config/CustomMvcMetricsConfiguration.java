package com.example.missingtag.config;

import com.example.missingtag.LowCardinalityOverrideClientRequestObservationConvention;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomMvcMetricsConfiguration {

    @Bean
    public LowCardinalityOverrideClientRequestObservationConvention clientNameOverridingClientRequestObservationConvention() {
        return new LowCardinalityOverrideClientRequestObservationConvention();
    }
}
