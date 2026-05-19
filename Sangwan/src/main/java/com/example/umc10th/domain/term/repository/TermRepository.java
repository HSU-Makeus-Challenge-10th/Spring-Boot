package com.example.umc10th.domain.term.repository;

import com.example.umc10th.domain.term.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findByIsRequiredTrue();
}
