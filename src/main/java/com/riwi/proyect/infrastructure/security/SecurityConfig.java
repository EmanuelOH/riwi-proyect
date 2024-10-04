package com.riwi.proyect.infrastructure.security;

import com.riwi.proyect.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {
    @Autowired
    private final JwtFilter jwtFilter;

    @Autowired
    private final AuthenticationProvider authenticationProvider;

    private final String [] PUBLIC_ENDPOINT = {
            "/auth/login",
            "/users/register/student",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    private final String [] ADMIN_ENDPOINT = {
            "/user/admin/register",
            "/project/**",
            "/task/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ADMIN_ENDPOINT).hasAuthority(RoleEnum.ADMIN.name())
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
