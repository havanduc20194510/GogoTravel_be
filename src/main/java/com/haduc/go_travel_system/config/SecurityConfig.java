package com.haduc.go_travel_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomJwtDecoder customJwtDecoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.GET, "/").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/logout").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/introspect").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/refresh").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/token").permitAll()
                .requestMatchers(HttpMethod.POST, "/suggestion/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/places/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/tour/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/tour/increase-view/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/tour-schedule/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/task/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/schedule-detail/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/departure-time/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/tour-type/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/suggestion/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "departure-time/update/available/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/tour-reviews/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/tour-reviews/average-rating/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/tour-reviews/create").permitAll()
                .requestMatchers(HttpMethod.PUT, "/tour-reviews/update/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/payment/vn-pay/check-payment/**").permitAll()

                .requestMatchers("/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated());
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(
                jwtConfigurer -> jwtConfigurer.decoder(customJwtDecoder)
                .jwtAuthenticationConverter(jwtAuthenticationConverter())));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder(10);
    }
}
