package com.example.umc10th.domain.point.entity;

import com.example.umc10th.domain.point.enums.WithdrawalStatus;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "point_withdrawal")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class PointWithdrawal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false, length = 50)
    private String bankName;

    @Column(nullable = false, length = 50)
    private String accountNumber;

    @Column(nullable = false, length = 50)
    private String accountHolder;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WithdrawalStatus status;

    private String rejectReason;

    @OneToMany(mappedBy = "pointWithdrawal", fetch = FetchType.LAZY)
    private List<PointHistory> pointHistories = new ArrayList<>();
}
