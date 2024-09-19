package com.chis.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class that defines a {@link RestTemplate} bean to be used throughout the application.
 * This class enables the central configuration and management of {@code RestTemplate}, allowing it to be injected
 * into other Spring-managed components wherever HTTP client functionality is required. By defining
 * {@code RestTemplate} as a bean, we ensure that it is consistently configured and reusable across the application.
 * @return a configured instance of {@link RestTemplate}
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
