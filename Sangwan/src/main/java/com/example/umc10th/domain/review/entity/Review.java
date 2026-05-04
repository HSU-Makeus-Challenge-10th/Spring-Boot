package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_mission_id", nullable = false)
    private MemberMission memberMission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private Double rating;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewImage> reviewImages = new ArrayList<>();

    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY)
    private ReviewAnswer reviewAnswer;
}
