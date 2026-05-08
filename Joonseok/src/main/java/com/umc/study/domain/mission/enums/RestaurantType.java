package com.umc.study.domain.mission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestaurantType {
    KOREAN("한식당"),
    JAPANESE("일식당"),
    CHINESE("중식당"),
    WESTERN("양식당"),
    CHICKEN("치킨집"),
    STREET("분식집"),
    GRILLED("고깃집"),
    BENTO("도시락집"),
    LATE_MEAL("야식집"),
    FAST_FOOD("패스트푸드점"),
    DESSERT("디저트가게"),
    ASIAN("아시안 음식점");

    private final String korean;
}
