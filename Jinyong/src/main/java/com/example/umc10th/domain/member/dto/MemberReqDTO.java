package com.example.umc10th.domain.member.dto;

import lombok.Getter;
import java.util.List;

public class MemberReqDTO {

    @Getter
    public static class JoinDTO {
        String name;
        String gender;
        String nickname;
        String email;
        String password;
        String birth;
        String phoneNumber;
        List<TermsAgreementDTO> termsAgreements;
    }

    @Getter
    public static class TermsAgreementDTO {
        Long termsId;
        Boolean isAgreed;
    }
}
