package com.example.umc10thweek4.domain.ask.entity;

import com.example.umc10thweek4.domain.ask.entity.Ask;
import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ask_image")
public class AskImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ask_id", nullable = false)
    private Ask ask;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}