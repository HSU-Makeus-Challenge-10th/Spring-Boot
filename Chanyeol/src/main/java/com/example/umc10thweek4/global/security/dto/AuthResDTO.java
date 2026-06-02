package com.example.umc10thweek4.global.security.dto;

public class AuthResDTO {

    public record Login(
            String tokenType,
            String accessToken
    ) {
        public static Login of(String accessToken) {
            return new Login("Bearer", accessToken);
        }
    }
}
