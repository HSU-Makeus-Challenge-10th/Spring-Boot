package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;

public class StoreConverter {

    public static StoreResDTO.StoreInfo toStoreInfo(Store store) {
        return StoreResDTO.StoreInfo.builder()
                .id(store.getId())
                .regionId(store.getRegion().getId())
                .name(store.getName())
                .address(store.getAddress())
                .openingHours(store.getOpeningHours())
                .isOpen(store.getIsOpen())
                .build();
    }
}