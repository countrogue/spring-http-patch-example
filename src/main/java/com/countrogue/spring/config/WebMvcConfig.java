package com.countrogue.spring.config;

import com.countrogue.spring.config.patch.resolver.PartialUpdateArgumentResolver;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.ZonedDateTime;
import java.util.List;

import static com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer.ZONED_DATE_TIME;

/**
 * Created by socrates on 8/30/16.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired ApplicationContext applicationContext;
    @Autowired PartialUpdateArgumentResolver partialUpdateArgumentResolver;

    /**
     * Adds the patch argument resolver
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(partialUpdateArgumentResolver);
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder
                .serializerByType(ZonedDateTime.class, ZonedDateTimeSerializer.INSTANCE)
                .deserializerByType(ZonedDateTime.class, ZONED_DATE_TIME)
                .applicationContext(applicationContext);
        return builder;
    }
}
