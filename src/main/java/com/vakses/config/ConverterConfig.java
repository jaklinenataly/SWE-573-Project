package com.vakses.config;

import com.vakses.converter.DonationDtoToDonationEntityConverter;
import com.vakses.converter.DonationEntityToDonationEntityResourceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.support.ConfigurableConversionService;

import javax.annotation.PostConstruct;

/**
 * Created by veraxmedax on 15/03/2018.
 */
@Configuration
public class ConverterConfig {
    @Autowired
    private ConfigurableConversionService conversionService;

    @PostConstruct
    public void init() {
        conversionService.addConverter(new DonationEntityToDonationEntityResourceConverter());
        conversionService.addConverter(new DonationDtoToDonationEntityConverter());
    }

    @Bean
    @Primary
    public ConfigurableConversionService getConversionService() {
        return conversionService;
    }
}
