package com.example.umc10thweek4.domain.region.service;

import com.example.umc10thweek4.domain.region.dto.RegionResDTO;
import com.example.umc10thweek4.domain.region.entity.Region;
import com.example.umc10thweek4.domain.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionResDTO.RegionList getAllRegions() {
        List<Region> regions = regionRepository.findAllByDeletedAtIsNullOrderByRegionNameAsc();

        List<RegionResDTO.RegionInfo> regionInfos = regions.stream()
                .map(r -> new RegionResDTO.RegionInfo(r.getId(), r.getRegionName(), r.getRegionImage()))
                .toList();

        return new RegionResDTO.RegionList(regionInfos);
    }

    public RegionResDTO.RegionInfo getRegion(Long regionId) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RuntimeException("Region not found")); // 추후 ExceptionHandler로 교체

        return new RegionResDTO.RegionInfo(region.getId(), region.getRegionName(), region.getRegionImage());
    }
}