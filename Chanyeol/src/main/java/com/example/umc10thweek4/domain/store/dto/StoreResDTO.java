package com.example.umc10thweek4.domain.store.dto;

import com.example.umc10thweek4.domain.mission.dto.MissionResDTO;

import java.util.List;

public class StoreResDTO {

    public record StoreInfo(
            Long storeId,
            String storeName,
            String address,
            String storeImage,
            String category,
            String regionName
    ) {}

    public record StoreList(
            List<StoreInfo> stores
    ) {}

    public record StoreDetail(
            Long storeId,
            String storeName,
            String address,
            String storeImage,
            String category,
            String regionName
    ) {}

    public record StoreWithMissions(
            StoreDetail store,
            List<MissionResDTO.Home.RecommendedMission> missions
    ) {}
}