package com.example.umc10th.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] allowUris = {
      //swagger 허용
      // 자유롭게 이용할 수 있는 주소 (비로그인)
      "swagger-ui/**",
      "/swagger-resources/**",
      "/v3/api-docs/**",
      //로그인
      "/auth/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // csrf 방어기능 끄기
                // 위의 허용한 경로로 요청이 들어오면 모두 허용
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )
                //스프링 시큐리티가 제공하는 기본 로그인 폼을 사용
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui.html", true)
                        .permitAll()
                //로그아웃 주소로 요청을 보내면 로그아웃 처리를 진행
                ).logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 로그인 페이지로 리다이렉트
                        .permitAll()
                );
        return http.build();
    }

    //해시 알고리즘을 이용해 함호화된 Bcrypt 알고리즘 객체 반환
    //알고리즘은 실행때마다 매번 다른 결과물 생성
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}