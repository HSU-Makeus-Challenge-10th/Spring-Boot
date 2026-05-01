package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "terms") // ERD 기준 테이블명 'terms'
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255) // ERD의 varchar 대응
    private String name;

    @Column(nullable = false, columnDefinition = "text") // ERD의 text 대응
    private String content;

    @Column(name = "is_required", nullable = false) // ERD의 boolean 대응
    private Boolean isRequired;
}