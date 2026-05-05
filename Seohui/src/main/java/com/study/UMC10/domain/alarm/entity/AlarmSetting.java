package com.study.UMC10.domain.alarm.entity;

import com.study.UMC10.domain.user.entity.User;
import com.study.UMC10.global.apiPayload.code.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "alarm_setting")
public class AlarmSetting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_id")
    private Long id;

    @Column(name = "event_alarm", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean eventAlarm;

    @Column(name = "review_alarm", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean reviewAlarm;

    @Column(name = "inquiry_alarm", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean inquiryAlarm;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}