package com.example.umc10thweek4.domain.member.entity;


import com.example.umc10thweek4.domain.member.enums.Gender;
import com.example.umc10thweek4.domain.member.enums.SocialType;
import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(name = "total_points")
    private Long totalPoints = 0L;

    @Column(name = "complete_num")
    private Integer completeNum = 0;

    // 소셜 로그인 관련 (필요 시)
    @Column(name = "social_uid")
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    public void addPoints(Long points) {
        this.totalPoints += points;
        this.completeNum += 1;        // 완료 미션 수 증가
    }
}