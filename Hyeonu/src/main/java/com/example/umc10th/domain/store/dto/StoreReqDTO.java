package com.example.umc10th.domain.store.dto;


public class StoreReqDTO {

    // 가게 등록 요청
    public record JoinDTO(
        String name, // 가게 이름
        String address, // 각 주소
        Long regionId // 지역ID
    ){}
}
