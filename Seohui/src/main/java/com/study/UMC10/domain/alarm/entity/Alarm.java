package com.study.UMC10.domain.alarm.entity;

import com.study.UMC10.domain.alarm.enums.AlarmType;
import com.study.UMC10.domain.user.entity.User;
import com.study.UMC10.global.apiPayload.code.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alarm")
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "alarm_type", nullable = false)
    private AlarmType alarmType;

    @Column(name = "alarm_content", columnDefinition = "TEXT", nullable = false)
    private String alarmContent;

    @Column(name = "is_read", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}