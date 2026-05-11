package com.example.umc10th.domain.store.dto;

import lombok.Builder;
import java.time.LocalDateTime;

public class StoreResDTO {
    @Builder
    public record StoreInfo(
            Long id,
            String name,
            String address,
            String regionName
    ){}

    @Builder
    public record RegisterResult(
            Long storeId,
            LocalDateTime createdAt
    ){}
}