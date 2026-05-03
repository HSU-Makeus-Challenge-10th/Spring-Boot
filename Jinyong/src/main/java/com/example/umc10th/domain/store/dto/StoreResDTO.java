package com.example.umc10th.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {

    @Builder
    public record StoreInfo (
            Long id,
            Long regionId,
            String name,
            String address,
            String openingHours,
            Boolean isOpen
    ) {}
}
