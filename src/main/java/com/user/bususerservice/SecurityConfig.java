package com.user.bususerservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Value("${spring.security.oauth2.resourceserver.opaque.introspection-uri}")
        String introspectionUri;

        @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-id}")
        String clientId;

        @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-secret}")
        String clientSecret;



        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeRequests(authz ->authz
                             .anyRequest()
                            .authenticated())
                    .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken
                            (token -> token.introspectionUri(this.introspectionUri)
                                    .introspectionClientCredentials(this.clientId, this.clientSecret)));
            return http.build();
        }

}
