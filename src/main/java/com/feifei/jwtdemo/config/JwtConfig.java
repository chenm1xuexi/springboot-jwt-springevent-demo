package com.feifei.jwtdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@Configuration
public class JwtConfig {

    @Bean
    @ConfigurationProperties(prefix = "audience")
    public Audience audience() {
        return new Audience();
    }
}