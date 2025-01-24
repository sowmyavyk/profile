package com.profilemanagement.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF for development purposes
            .authorizeHttpRequests()
                .anyRequest().permitAll() // Disable all authentication
            .and()
            .httpBasic().disable(); // Disable basic authentication

        return http.build();
    }
}