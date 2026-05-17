package com.example.umc10th.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Spring Security 설정을 활성화시키는 역할을 함
@Configuration
public class SecurityConfig {

    private final String[] allowUris = { // 허용할 URI, Public API를 따로 빼서 관리함
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",

            // 로그인
            "/auth/**"
    };

    @Bean // SecurityFilterChain을 정의함. HttpSecurity객체를 통해 다양한 보안 설정 구성 가능
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests // HTTP요청에 대한 접근 제어 설정
                        .requestMatchers(allowUris).permitAll() // permitAll()은 인증 없이 접근 가능한 경로 지정
                        .anyRequest().authenticated() // 그 외 모든 요청에 대한 인증 요구
                )
                .formLogin(form -> form // 폼 기반 로그인에 대한 설정
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )
                .logout(logout -> logout // /logout 경로로 로그아웃 처리
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 /login?logout으로 리다이렉트
                        .permitAll()
                );

        return http.build();
    }

    // 비밀번호 솔트를 위한  BCrypt를 PasswordEncoder로 설정
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
