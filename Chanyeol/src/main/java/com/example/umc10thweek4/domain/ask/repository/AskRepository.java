package com.example.umc10thweek4.domain.ask.repository;

import com.example.umc10thweek4.domain.ask.entity.Ask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AskRepository extends JpaRepository<Ask, Long> {

    Optional<Ask> findByIdAndDeletedAtIsNull(Long id);

    // 내가 작성한 문의 목록
    List<Ask> findByMemberIdAndDeletedAtIsNullOrderByCreatedAtDesc(Long memberId);

    // 문의 상세 조회
    @Query("SELECT a FROM Ask a WHERE a.id = :askId AND a.deletedAt IS NULL")
    Optional<Ask> findDetailById(@Param("askId") Long askId);
}