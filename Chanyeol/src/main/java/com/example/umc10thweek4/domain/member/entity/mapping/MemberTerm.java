package com.example.umc10thweek4.domain.member.entity.mapping;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.entity.Term;
import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_terms")
public class MemberTerm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id", nullable = false)
    private Term term;

    @Column(name = "is_agreed", nullable = false)
    private Boolean isAgreed = false;
}