package com.example.umc10th.global.config;

import com.example.umc10th.global.security.handler.CustomAccessDenied;
import com.example.umc10th.global.security.handler.CustomEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Spring Security 활성화
@Configuration // 스프링 설정 파일임을 선언
public class SecurityConfig {

    // 허용 URI 목록
    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**", // API 문서 UI
            "/swagger-resources/**", // Swagger 리소스
            "/v3/api-docs/**", // Openapi 스펙 문서
            "/auth/**" // 인증 관련 엔드 포인트(로그인, 회원가입 등)
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // RestAPI는 보통 CSRF 공격에 덜 취약하므로 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // 요청 권한 설정
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll() // allowUris는 누구나 접근 가능
                        .anyRequest().authenticated() // 그 외 모든 요청은 로그인 필요
                )
                // 폼 로그인 설정
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true) // 로그인 성공 시 이동
                        .permitAll() // 로그인 페이지는 모든 사용자가 접근 가능
                )
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/logout") // 이 URL로 POST 요청 시 로그아웃
                        .logoutSuccessUrl("/login?logout") // 로그아웃 후 로그인 페이지로 이동
                        .permitAll()
                )
                // 예외 상황 핸들러
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDenied()) // 403 발생
                        .authenticationEntryPoint(customEntryPoint())) //401 발생
        ;


        return http.build();
    }

    // PasswordEncoder- 비밀번호 암호화
    // BCrypt 알고리즘으로 해시 암호화(같은 비밀번호도 매번 다른 해시값이 생성되어 암호화)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //
    @Bean
    public CustomAccessDenied customAccessDenied(){
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint(){
        return new CustomEntryPoint();
    }
}
