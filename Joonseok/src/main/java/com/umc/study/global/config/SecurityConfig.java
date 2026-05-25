package com.umc.study.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.study.global.jwt.JwtTokenFilter;
import com.umc.study.global.security.CustomEntryPoint;
import com.umc.study.global.security.exception.CustomAccessDenied;
import com.umc.study.global.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    private final JwtTokenFilter jwtTokenFilter;
    private final CustomUserDetailsService customUserDetailsService;

    private final String[] allowUris = {
            "/api/swagger-ui/**",
            "/api/swagger-resources/**",
            "/api/v3/api-docs/**",
            "/api/auth/**"                      // sign-up, login request allow
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAccessDenied customAccessDenied, CustomEntryPoint customEntryPoint) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)

                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .exceptionHandling(e -> e
                        .accessDeniedHandler(customAccessDenied)
                        .authenticationEntryPoint(customEntryPoint));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
