package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    //StoreDTO
    public static StoreResDTO.StoreDTO toStoreDto(Store store) {
        return StoreResDTO.StoreDTO.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .address(store.getAddress())
                .region(store.getRegion().getRegionName())
                .build();
    }

    // List 반환
    public static List<StoreResDTO.StoreDTO> toStoreDtoList(List<Store> stores) {
        return stores.stream()
                .map(StoreConverter::toStoreDto)
                .collect(Collectors.toList());
    }
}
