package com.example.system.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by admin on 2019-10-17.
 */

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfiguration.class);

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        LOGGER.info("WebMVC configuration : addCorsMappings");
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
