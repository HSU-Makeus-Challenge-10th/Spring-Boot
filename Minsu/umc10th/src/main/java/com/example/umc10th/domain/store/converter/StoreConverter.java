package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.region.entity.Town;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;

public class StoreConverter {

    public static StoreResDTO.StoreInfo toStoreInfo(Store store, Double avgRating, Long reviewCount) {
        return StoreResDTO.StoreInfo.builder()
                .storeId(String.valueOf(store.getId()))
                .name(store.getName())
                .foodType(store.getFoodType())
                .detailAddress(store.getDetailAddress())
                .avgRating(avgRating != null ? Math.round(avgRating * 10.0) / 10.0 : 0.0)
                .reviewCount(reviewCount != null ? reviewCount : 0L)
                .isOpen(store.getIsOpen())
                .imgUrl(store.getImgUrl())
                .lat(store.getLat())
                .lng(store.getLng())
                .build();
    }

    public static StoreResDTO.HomeInfo toHomeInfo(Town town, Long aramCount, Long ongoingMissionCount) {
        return StoreResDTO.HomeInfo.builder()
                .townName(town != null ? town.getName() : "전체")
                .aramCount(aramCount != null ? aramCount : 0L)
                .ongoingMissionCount(ongoingMissionCount != null ? ongoingMissionCount : 0L)
                .build();
    }
}
