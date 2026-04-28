package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.ActivatedMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.region.entity.Town;
import com.example.umc10th.domain.region.repository.TownRepository;
import com.example.umc10th.domain.store.converter.StoreConverter;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final ActivatedMissionRepository activatedMissionRepository;
    private final TownRepository townRepository;

    public StoreResDTO.StoreInfo getStoreById(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        Double avgRating = storeRepository.findAvgRatingByStoreId(storeId);
        Long reviewCount = storeRepository.countReviewsByStoreId(storeId);
        return StoreConverter.toStoreInfo(store, avgRating, reviewCount);
    }

    public MissionResDTO.StoreMissionPageResult getStoreMissions(Long storeId, Long cursor, int limit) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        List<Mission> list = missionRepository.findByStoreIdCursor(storeId, cursor, PageRequest.of(0, limit));
        return MissionConverter.toStoreMissionPageResult(list, limit);
    }

    public StoreResDTO.HomeInfo getHomeInfo(Long townId) {
        Town town = null;
        Long aramCount = 0L;
        Long ongoingCount = 0L;

        if (townId != null) {
            town = townRepository.findById(townId).orElse(null);
            ongoingCount = activatedMissionRepository.countOngoingByTownId(townId);
        }

        return StoreConverter.toHomeInfo(town, aramCount, ongoingCount);
    }
}
