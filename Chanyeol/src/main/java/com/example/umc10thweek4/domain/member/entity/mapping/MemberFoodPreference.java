package com.example.umc10thweek4.domain.member.entity.mapping;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "food_preference")
public class MemberFoodPreference extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(name = "food", nullable = false)
    private String food;   // 예: "한식", "중식", "일식" 등
}