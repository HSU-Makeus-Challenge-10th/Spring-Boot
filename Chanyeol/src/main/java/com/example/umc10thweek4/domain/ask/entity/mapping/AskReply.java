package com.example.umc10thweek4.domain.ask.entity.mapping;

import com.example.umc10thweek4.domain.ask.entity.Ask;
import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ask_reply")
public class AskReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ask_id", nullable = false)
    private Ask ask;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "reply_created_at", nullable = false)
    private LocalDateTime replyCreatedAt;
}