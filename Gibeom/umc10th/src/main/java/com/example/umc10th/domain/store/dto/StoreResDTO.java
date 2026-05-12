package com.example.umc10th.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {
    @Builder
    public record StoreDTO(
      Long storeId,
      String storeName,
      String address,
      String region
    ){}
}
