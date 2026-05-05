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
@Table(name = "notice_setting")
public class MemberNoticeSetting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(name = "get_new_event", nullable = false)
    private Boolean getNewEvent = true;

    @Column(name = "get_review_comment", nullable = false)
    private Boolean getReviewComment = true;

    @Column(name = "get_ask_comment", nullable = false)
    private Boolean getAskComment = true;
}