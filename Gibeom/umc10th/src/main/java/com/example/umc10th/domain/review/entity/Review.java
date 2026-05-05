package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "score", precision = 3, scale = 2, nullable = false)
    private int score;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @OneToOne(mappedBy = "review")
    private OwnerComment ownerComment;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<ReviewImg> ImgList = new ArrayList<>();
}
