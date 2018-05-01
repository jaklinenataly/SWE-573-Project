package com.vakses.config;

import com.vakses.converter.DonationDtoToDonationEntityConverter;
import com.vakses.converter.DonationEntityToDonationEntityResourceConverter;
import com.vakses.converter.SubscriptionSetToSubscriptionResourceSetConverter;
import com.vakses.converter.UserDtoToUserConverter;
import com.vakses.converter.UserToUserResourceConverter;
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
        conversionService.addConverter(new UserDtoToUserConverter());
        conversionService.addConverter(new UserToUserResourceConverter());
        conversionService.addConverter(new SubscriptionSetToSubscriptionResourceSetConverter());
    }

    @Bean
    @Primary
    public ConfigurableConversionService getConversionService() {
        return conversionService;
    }
}
