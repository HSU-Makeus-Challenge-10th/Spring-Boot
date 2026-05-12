package com.example.umc10thweek4.domain.store.converter;

import com.example.umc10thweek4.domain.store.dto.StoreResDTO;
import com.example.umc10thweek4.domain.store.entity.Store;

import java.util.List;

public class StoreConverter {

    public static StoreResDTO.StoreInfo toStoreInfo(Store store) {
        return new StoreResDTO.StoreInfo(
                store.getId(),
                store.getStoreName(),
                store.getAddress(),
                store.getStoreImage(),
                store.getCategory(),
                store.getRegion().getRegionName()
        );
    }

    public static StoreResDTO.StoreList toStoreList(List<Store> stores) {
        return new StoreResDTO.StoreList(
                stores.stream()
                        .map(StoreConverter::toStoreInfo)
                        .toList()
        );
    }

    public static StoreResDTO.StoreDetail toStoreDetail(Store store) {
        return new StoreResDTO.StoreDetail(
                store.getId(),
                store.getStoreName(),
                store.getAddress(),
                store.getStoreImage(),
                store.getCategory(),
                store.getRegion().getRegionName()
        );
    }
}