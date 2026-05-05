package com.example.umc10thweek4.domain.ask.entity;

import com.example.umc10thweek4.domain.ask.enums.AskType;
import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ask")
public class Ask extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(name = "ask_title", nullable = false)
    private String askTitle;

    @Column(name = "ask_detail", nullable = false, columnDefinition = "TEXT")
    private String askDetail;

    @Enumerated(EnumType.STRING)
    @Column(name = "ask_type", nullable = false)
    private AskType askType;

    @Column(nullable = false)
    private String status;

    @Column(name = "ask_time", nullable = false)
    private LocalDateTime askTime;
}