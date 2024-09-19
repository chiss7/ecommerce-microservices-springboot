package com.chis.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * This class configures security for the API gateway using Spring Security's WebFlux module.
 * The security is integrated with Keycloak for OAuth2-based authentication using JWT tokens.
 * - CSRF protection is disabled for this gateway.
 * - All requests to the "/eureka/**" path are allowed without authentication (useful for service registry communication).
 * - Any other request requires authentication, which is handled by Keycloak via OAuth2 and JWT tokens.
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * Configures security filters for incoming HTTP requests.
     * @param serverHttpSecurity an instance of {@link ServerHttpSecurity} used to build the security configuration.
     * @return the configured {@link SecurityWebFilterChain}, which defines the security rules for the application.
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                // Disable CSRF protection for the API gateway, which is typical in token-based authentication.
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                // Define rules for authorizing incoming requests.
                .authorizeExchange(exchange -> exchange
                        // Allow all requests to paths starting with "/eureka/" (usually for service registry).
                        .pathMatchers("/eureka/**")
                        .permitAll()
                        // Require authentication for any other request.
                        .anyExchange()
                        .authenticated()
                )
                // Enable OAuth2 resource server to handle JWT tokens for authentication.
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return serverHttpSecurity.build();
    }
}
