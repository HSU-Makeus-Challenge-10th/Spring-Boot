package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}
