package com.example.umc10th.global.config;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // REST API와 Postman 실습 흐름에서는 CSRF 토큰을 함께 보내지 않으므로 비활성화한다.
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/members/signup",
                                "/api/v1/members/login",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/api/v1/members/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler((request, response, authentication) ->
                                writeSuccessResponse(response, GeneralSuccessCode.OK))
                        .failureHandler((request, response, exception) ->
                                writeErrorResponse(response, GeneralErrorCode.UNAUTHORIZED))
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/api/v1/members/logout")
                        .logoutSuccessHandler((request, response, authentication) ->
                                writeSuccessResponse(response, GeneralSuccessCode.OK))
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) ->
                                writeErrorResponse(response, GeneralErrorCode.UNAUTHORIZED))
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                writeErrorResponse(response, GeneralErrorCode.FORBIDDEN))
                );

        return http.build();
    }

    private void writeSuccessResponse(HttpServletResponse response, BaseSuccessCode code) throws IOException {
        writeResponse(response, code.getStatus().value(), true, code.getCode(), code.getMessage());
    }

    private void writeErrorResponse(HttpServletResponse response, BaseErrorCode code) throws IOException {
        writeResponse(response, code.getStatus().value(), false, code.getCode(), code.getMessage());
    }

    private void writeResponse(HttpServletResponse response, int status, boolean isSuccess,
                               String code, String message) throws IOException {
        response.setStatus(status);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(
                "{\"isSuccess\":%s,\"code\":\"%s\",\"message\":\"%s\",\"result\":null}"
                        .formatted(isSuccess, escapeJson(code), escapeJson(message))
        );
    }

    private String escapeJson(String value) {
        StringBuilder escaped = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            switch (ch) {
                case '"' -> escaped.append("\\\"");
                case '\\' -> escaped.append("\\\\");
                case '\b' -> escaped.append("\\b");
                case '\f' -> escaped.append("\\f");
                case '\n' -> escaped.append("\\n");
                case '\r' -> escaped.append("\\r");
                case '\t' -> escaped.append("\\t");
                default -> {
                    if (ch < 0x20) {
                        escaped.append(String.format("\\u%04x", (int) ch));
                    } else {
                        escaped.append(ch);
                    }
                }
            }
        }
        return escaped.toString();
    }
}
