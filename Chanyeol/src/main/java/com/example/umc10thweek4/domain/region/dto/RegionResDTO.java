package com.example.umc10thweek4.domain.region.dto;

import java.util.List;

public class RegionResDTO {

    public record RegionInfo(
            Long regionId,
            String regionName,
            String regionImage
    ) {}

    public record RegionList(
            List<RegionInfo> regions
    ) {}

    public record HomeRegion(
            Long regionId,
            String regionName
    ) {}
}