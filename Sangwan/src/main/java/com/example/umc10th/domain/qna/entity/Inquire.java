package com.example.umc10th.domain.qna.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.qna.enums.InquireStatus;
import com.example.umc10th.domain.qna.enums.InquireType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inquire")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Inquire extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private InquireType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InquireStatus status;

    @OneToMany(mappedBy = "inquire", fetch = FetchType.LAZY)
    private List<InquireImage> inquireImages = new ArrayList<>();

    @OneToOne(mappedBy = "inquire", fetch = FetchType.LAZY)
    private InquireAnswer inquireAnswer;
}
