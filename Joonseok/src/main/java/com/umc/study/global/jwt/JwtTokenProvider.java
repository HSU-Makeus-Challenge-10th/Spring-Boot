package com.umc.study.global.jwt;

import com.umc.study.domain.user.entity.User;
import com.umc.study.domain.user.exception.UserNotFoundException;
import com.umc.study.domain.user.repository.UserRepository;
import com.umc.study.global.security.entity.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;


@Component
public class JwtTokenProvider {

    private final UserRepository userRepository;

    private final String secretKey;
    private Key key;
    private final Duration expiration;

    // jwt signature를 final, env로 주입하는 생성자
    public JwtTokenProvider(
            UserRepository userRepository,
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration}")  Long expiration
    ) {
        this.userRepository = userRepository;
        this.secretKey = secretKey;
        this.expiration = Duration.ofMillis(expiration);
    }

    // signature -> Byte 배열 -> hmac sha 이용하여 Key 객체에 할당
    @PostConstruct
    public void init() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        if(keyBytes.length < 32) {
            throw new IllegalArgumentException("jwt.secret must be at least 256 bits (32 bytes)");
        }
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * <table border="1">
     *     <capation>Date와 Instant의 차이</capation>
     *     <colgroup>
     *
     *     </colgroup>
     *     <tr>
     *         <td>&nbsp;</td>
     *         <td>정밀도</td>
     *         <td>가변성</td>
     *         <td>thread-safety</td>
     *         <td>testable</td>
     *     </tr>
     *     <tr>
     *         <th>java.util.Date</th>
     *         <td>밀리초 단위</td>
     *         <td>mutable</td>
     *         <td>안전하지 않음</td>
     *         <td>유닛 테스트에서 시간 조작 불가</td>
     *     </tr>
     *     <tr>
     *         <th>java.time.Instant</th>
     *         <td>나노초 단위</td>
     *         <td>immutable</td>
     *         <td>안전</td>
     *         <td>유닛 테스트에서 시간 조작 가능</td>
     *     </tr>
     * </table>
     */
    public String generateToken(User user) {

        // 현재 시각을 기준으로 만료일 설정
        Instant now = Instant.now();
        Instant expiry = now.plus(expiration);

        return Jwts.builder()

                // subject : User의 고유 식별 필드
                .subject(String.valueOf(user.getId()))

                // claim : User에서 자주 참조되는 필드(DB 접근을 최소화하기 위해)
                .claim("email", user.getEmail())
                .claim("role", user.getRole() != null ? user.getRole() : null)

                // issuedAt : 토큰 발행 시각
                .issuedAt(Date.from(now))

                // expiration : 토큰 만료 시각
                .expiration(Date.from(expiry))

                // signWith : signature를 위한 키 설정 (default : HS256)
                .signWith(key)

                // JWT 토큰(subject.claim.signature)로 발행
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // JWT 토큰에서 사용자 정보를 추출하고, Spring Security에서 사용할 수 있게 Authentication 객체로 가공
    public Authentication getAuthentication(String token) {

        // extract Unique field
        Long id = Long.parseLong(getClaims(token).getSubject());

        User found = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        CustomUserDetails userDetails = new CustomUserDetails(found);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                token,
                userDetails.getAuthorities()
        );
    }

    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(Date.from(Instant.now()));
    }

}
