package com.study.UMC10.domain.review.entity;

import com.study.UMC10.domain.store.entity.Store;
import com.study.UMC10.domain.user.entity.User;
import com.study.UMC10.global.apiPayload.code.BaseEntity;
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
    @Column(name = "review_id")
    private Long id;

    @Column(name = "score", nullable = false)
    private Double score;

    @Column(name = "review_content", columnDefinition = "TEXT")
    private String reviewContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();

    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OwnerComment ownerComment;
}