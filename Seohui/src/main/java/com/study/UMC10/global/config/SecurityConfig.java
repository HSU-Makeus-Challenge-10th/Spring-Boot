package com.study.UMC10.global.config;

import com.study.UMC10.global.security.CustomAuthenticationEntryPoint;
import com.study.UMC10.global.security.filter.JwtAuthFilter;
import com.study.UMC10.global.security.handler.CustomAccessDeniedHandler;
import com.study.UMC10.global.security.handler.OAuthSuccessHandler;
import com.study.UMC10.global.security.service.CustomOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.webauthn.management.JdbcPublicKeyCredentialUserEntityRepository;
import org.springframework.security.web.webauthn.management.JdbcUserCredentialRepository;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final JwtAuthFilter jwtAuthFilter;
    private final CustomOAuthService customOAuthService;
    private final OAuthSuccessHandler oAuthSuccessHandler;

    // 인증 없이 접근 가능한 Public API 목록
    private final String[] allowUris = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/api/auth/**",
            "/oauth2/**",
            "/oauth/callback/**",
            "/login/**",
            "/passkey/**",
            "/auth/**",
            "/webauthn/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin(AbstractHttpConfigurer::disable)

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(auth -> auth
                                .baseUri("/oauth2/authorization")
                        )
                        .redirectionEndpoint(redirection -> redirection
                                .baseUri("/oauth/callback/*")
                        )
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuthService)
                        )
                        .successHandler(oAuthSuccessHandler)
                )

                .webAuthn(webAuthn -> webAuthn
                        .rpId("localhost")
                        .allowedOrigins("http://localhost:8080")
                        .disableDefaultRegistrationPage(true)
                )

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDeniedHandler)
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                );

        return http.build();
    }

    @Bean
    public JdbcPublicKeyCredentialUserEntityRepository jdbcPublicKeyCredentialRepository(JdbcOperations jdbc) {
        return new JdbcPublicKeyCredentialUserEntityRepository(jdbc);
    }

    @Bean
    public JdbcUserCredentialRepository jdbcUserCredentialRepository(JdbcOperations jdbc) {
        return new JdbcUserCredentialRepository(jdbc);
    }
}