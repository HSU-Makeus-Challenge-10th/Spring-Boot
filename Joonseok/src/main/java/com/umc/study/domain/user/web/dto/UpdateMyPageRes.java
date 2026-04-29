package com.umc.study.domain.user.web.dto;

import java.util.Optional;

public record UpdateMyPageRes(
        Optional<String> profileUrl,
        Optional<String> nickname
) {
}
