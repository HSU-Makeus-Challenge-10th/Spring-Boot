package com.umc.study.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger(OpenAPI 3.0) 설정 클래스
 * - API 문서 자동 생성을 위한 SpringDoc 설정
 * - JWT 인증 헤더를 Swagger UI에서 사용할 수 있도록 SecurityScheme 등록
 */
@Configuration
public class SwaggerConfig {

    /**
     * OpenAPI Bean 등록
     * - API 제목, 설명, 버전 등 기본 정보 설정
     * - JWT Bearer 토큰 인증 방식을 SecurityScheme으로 등록
     * - 모든 API 요청에 JWT 인증을 기본 적용
     *
     * @return OpenAPI 설정 객체
     */
    @Bean
    public OpenAPI swagger() {

        // API 기본 정보 설정
        Info info = new Info()
                .title("UMC10th")
                .description("10기 Swagger")
                .version("0.0.1");

        // JWT 인증 스키마 이름 정의 및 SecurityRequirement 생성
        String securityScheme = "JWT TOKEN";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securityScheme);

        // JWT Bearer 인증 방식의 SecurityScheme 컴포넌트 등록
        Components components = new Components()
                .addSecuritySchemes(securityScheme, new SecurityScheme()
                        .name(securityScheme)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("Bearer")
                        .bearerFormat("JWT"));

        // OpenAPI 객체 조립: 기본 정보 + 서버 URL + 보안 설정
        return new OpenAPI()
                .info(info)
                .addServersItem(new Server().url("/"))
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
