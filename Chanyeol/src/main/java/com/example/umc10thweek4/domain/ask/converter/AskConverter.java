package com.example.umc10thweek4.domain.ask.converter;

import com.example.umc10thweek4.domain.ask.dto.AskResDTO;
import com.example.umc10thweek4.domain.ask.entity.Ask;

import java.util.List;

public class AskConverter {

    public static AskResDTO.Create toCreateRes(Ask ask) {
        return new AskResDTO.Create(
                ask.getId(),
                ask.getAskTitle(),
                ask.getAskType().name(),
                ask.getCreatedAt()
        );
    }

    public static AskResDTO.GetList toGetListRes(Ask ask) {
        return new AskResDTO.GetList(
                ask.getId(),
                ask.getAskTitle(),
                ask.getAskType().name(),
                ask.getStatus(),
                ask.getCreatedAt()
        );
    }

    public static AskResDTO.GetDetail toGetDetailRes(Ask ask, List<String> imageUrls, AskResDTO.GetDetail.AskReply reply) {
        return AskResDTO.GetDetail.builder()
                .askId(ask.getId())
                .askTitle(ask.getAskTitle())
                .askDetail(ask.getAskDetail())
                .askType(ask.getAskType().name())
                .status(ask.getStatus())
                .createdAt(ask.getCreatedAt())
                .updatedAt(ask.getUpdatedAt())
                .imageUrls(imageUrls)
                .reply(reply)
                .build();
    }
}