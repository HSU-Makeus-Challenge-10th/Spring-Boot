package com.example.umc10th.domain.support.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.support.enums.QnaType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qna")
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "is_proceed")
    private Boolean isProceed;

    @Column(name = "type")
    private QnaType type;
}
