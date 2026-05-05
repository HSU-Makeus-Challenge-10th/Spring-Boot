package com.umc.study.domain.user.entity;

import com.umc.study.domain.user.enums.TermName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Term {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TermName termName;
}
