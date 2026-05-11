package com.example.umc10thweek4.domain.member.entity;

import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "terms")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "term_title", nullable = false)
    private String termTitle;

    @Column(name = "term_detail", nullable = false, columnDefinition = "TEXT")
    private String termDetail;

    @Column(name = "term_required", nullable = false)
    private Boolean termRequired = false;
}