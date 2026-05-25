package com.example.umc10th.global.security.util;

import com.example.umc10th.global.security.entity.AuthMember;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private final SecretKey secretKey; // JWT 서명에 쓸 키
    private final Duration accessExpiration; // 토큰 유효기간

    public JwtUtil(
            @Value("${jwt.token.secretKey}")String secret, // .env의 JWT_SECRET_KEY값
            @Value("${jwt.token.expiration.access}")Long accessExpiration // 1800000(30분)
    ){
        //문자열 시크릿키 ->HMAC-SHA 알고리즘용 SecretKey 객체로 변환
        this.secretKey= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        // 1800000ms -> Duration 객체로 변환
        this.accessExpiration=Duration.ofMillis(accessExpiration);
    }
    // AccessToken 생성
    public String createAccessToken(AuthMember member){
        return createToken(member,accessExpiration);
    }

    /** 토큰에서 이메일 가져오기
     *
     * 유저 정보를 추출할 토큰
     * 유저 이메일을 토큰에서 추출합니다
     */
    public String getEmail(String token){
        try{
            return getClaims(token).getPayload().getSubject(); // Parsing해서 Subject 가져오기
        }catch(JwtException e){
            return null;
        }
    }

    /** 토큰 유효성 확인
     *
     * 유효한지 확인할 토큰
     * True, False 반환합니다
     */
    public boolean isValid(String token){
        try{
            getClaims(token);
            return true;
        }catch(JwtException e){
            return false;
        }
    }

    // 토큰 생성
    private String createToken(AuthMember member,Duration expiration){
        Instant now= Instant.now(); // 현재 시각

        // member가 가진 권한 목록을 콤마로 이어붙임(ROLE_USER)
        // 인가 정보
        String authorities=member.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(member.getUserEmail()) // User 이메일을 Subject로
                .claim("role",authorities) // payload에 role 추가
                .claim("email",member.getUserEmail()) // payload에 email 추가
                .issuedAt(Date.from(now)) // 발급시각
                .expiration(Date.from(now.plus(expiration))) // 만료시각 (지금 +30분)
                .signWith(secretKey) // 시크릿 키로 서명
                .compact(); // 최종 JWT 문자열로 직렬화
    }
    // 토큰 정보 가져오기
    private Jws<Claims> getClaims(String token) throws JwtException{
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);
    }
}
