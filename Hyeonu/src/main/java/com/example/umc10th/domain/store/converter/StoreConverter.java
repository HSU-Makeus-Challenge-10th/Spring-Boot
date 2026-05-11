package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;

public class StoreConverter {
    public static StoreResDTO.StoreInfo toStoreInfo(Store store) {
        return StoreResDTO.StoreInfo.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .regionName(store.getRegion().getName())
                .build();
    }

    public static StoreResDTO.RegisterResult toRegisterResult(Store store) {
        return StoreResDTO.RegisterResult.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
