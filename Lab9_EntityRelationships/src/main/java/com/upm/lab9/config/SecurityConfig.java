package com.upm.lab9.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * PING WENCHAO 226969
 * SecurityConfig - Configuration class for Spring Security filters and authentication rules.
 * Modernized for Spring Boot 3.x / Spring Security 6.x using the SecurityFilterChain Bean.
 * Maintained for Lab 9 to secure the Enterprise Management System.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(this::configureAuthorization)
                .formLogin(this::configureFormLogin)
                .logout(this::configureLogout);

        return http.build();
    }

    private void configureAuthorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        authorize
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated();
    }

    private void configureFormLogin(FormLoginConfigurer<HttpSecurity> form) {
        form
                .loginPage("/login")
                .permitAll();
    }

    private void configureLogout(LogoutConfigurer<HttpSecurity> logout) {
        logout
                .permitAll();
    }
}