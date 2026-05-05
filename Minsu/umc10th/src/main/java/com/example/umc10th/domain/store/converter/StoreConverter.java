package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.region.entity.Town;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;

import java.util.List;

public class StoreConverter {

    public static StoreResDTO.StoreInfo toStoreInfo(Store store, Double avgRating, Long reviewCount, String imgUrl) {
        return StoreResDTO.StoreInfo.builder()
                .storeId(String.valueOf(store.getId()))
                .name(store.getName())
                .foodType(store.getFoodType())
                .detailAddress(store.getDetailAddress())
                .avgRating(avgRating != null ? Math.round(avgRating * 10.0) / 10.0 : 0.0)
                .reviewCount(reviewCount != null ? reviewCount : 0L)
                .isOpen(store.getIsOpen())
                .imgUrl(imgUrl != null ? imgUrl : store.getImgUrl())
                .lat(store.getLat())
                .lng(store.getLng())
                .build();
    }

    public static StoreResDTO.StoreListItem toStoreListItem(Store store) {
        return StoreResDTO.StoreListItem.builder()
                .storeId(String.valueOf(store.getId()))
                .name(store.getName())
                .foodType(store.getFoodType())
                .detailAddress(store.getDetailAddress())
                .isOpen(store.getIsOpen())
                .imgUrl(store.getImgUrl())
                .distanceKm(0.0) // TODO: 사용자 좌표가 생기면 거리 계산으로 교체
                .build();
    }

    public static StoreResDTO.StoreList toStoreList(List<Store> stores, int limit) {
        boolean hasNext = stores.size() == limit;
        String nextCursor = stores.isEmpty() ? null : String.valueOf(stores.get(stores.size() - 1).getId());
        return StoreResDTO.StoreList.builder()
                .stores(stores.stream().map(StoreConverter::toStoreListItem).toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
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
