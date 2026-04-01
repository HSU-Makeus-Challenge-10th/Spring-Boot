package com.umc.junho.user.repository;

import com.umc.junho.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
