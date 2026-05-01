package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_phone_info") // ERD 테이블명 반영
public class MemberPhoneInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ERD 기준 PK

    @Column(name = "user_id", nullable = false)
    private Long userId; // ERD의 user_id FK

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;
}