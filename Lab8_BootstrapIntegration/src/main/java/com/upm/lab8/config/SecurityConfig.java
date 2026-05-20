package com.upm.lab8.config;

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
 * Modernized for Spring Boot 3.x / Spring Security 6.x using the SecurityFilterChain Bean with method references.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures web-based security profiles for incoming HTTP requests using method references.
     * Defines public access rules, custom login routing, and logout procedures.
     * @param http The HttpSecurity object used to build the security filter chain.
     * @return The configured SecurityFilterChain instance.
     * @throws Exception If an error occurs during the security filter initialization.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(this::configureAuthorization)
                .formLogin(this::configureFormLogin)
                .logout(this::configureLogout);

        return http.build();
    }

    /**
     * Establishes request authorization rules mapping URL patterns to security filters.
     * @param authorize The registry collaborator used to configure URL matchers.
     */
    private void configureAuthorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        authorize
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated();
    }

    /**
     * Sets up form-based login authentication routing and parameters.
     * @param form The configurer collaborator used to define custom login view paths.
     */
    private void configureFormLogin(FormLoginConfigurer<HttpSecurity> form) {
        form
                .loginPage("/login")
                .permitAll();
    }

    /**
     * Defines user session invalidation and logout behaviors.
     * @param logout The configurer collaborator used to define custom logout behaviors.
     */
    private void configureLogout(LogoutConfigurer<HttpSecurity> logout) {
        logout
                .permitAll();
    }
}