package com.example.umc10th.domain.term.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberAgreement;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "term")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Boolean isRequired;

    @Column(length = 10)
    private String version;

    @OneToMany(mappedBy = "term", fetch = FetchType.LAZY)
    private List<MemberAgreement> memberAgreements = new ArrayList<>();
}
