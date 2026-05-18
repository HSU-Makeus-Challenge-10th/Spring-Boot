package com.example.umc10thweek4.domain.region.converter;

import com.example.umc10thweek4.domain.region.dto.RegionResDTO;
import com.example.umc10thweek4.domain.region.entity.Region;

import java.util.List;

public class RegionConverter {

    public static RegionResDTO.RegionInfo toRegionInfo(Region region) {
        return new RegionResDTO.RegionInfo(
                region.getId(),
                region.getRegionName(),
                region.getRegionImage()
        );
    }

    public static RegionResDTO.RegionList toRegionList(List<Region> regions) {
        return new RegionResDTO.RegionList(
                regions.stream()
                        .map(RegionConverter::toRegionInfo)
                        .toList()
        );
    }
}