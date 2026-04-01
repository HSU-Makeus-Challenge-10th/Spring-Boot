package com.umc.junho.user.service;

import com.umc.junho.user.entity.Member;
import com.umc.junho.user.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(String name, String email) {
        if (name == null || name.isBlank()) {
            name = "guest";
        }

        if (email == null || email.isBlank()) {
            email = name + "@test.com";
        }

        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            if (member.getEmail().equals(email)) {
                throw new RuntimeException("이미 존재하는 이메일입니다.");
            }
        }

        if (email.contains("admin")) {
            System.out.println("관리자 같은 이메일입니다.");
        }

        Member member = new Member(name, email);
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member updateMemberName(Long id, String name) {
        Member member = memberRepository.findById(id).orElse(null);

        if (name == null || name.isBlank()) {
            name = "anonymous";
        }

        member.setName(name);
        return memberRepository.save(member);
    }
}
