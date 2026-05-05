package com.umc.study.domain.review.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.umc.study.domain.mission.entity.Restaurant;
import com.umc.study.domain.user.entity.User;
import com.umc.study.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Double score;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "review")
    @JsonManagedReference
    private List<ReviewComment> comments;
}
