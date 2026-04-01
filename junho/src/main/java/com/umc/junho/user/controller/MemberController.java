package com.umc.junho.user.controller;

import com.umc.junho.user.entity.Member;
import com.umc.junho.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member create(@RequestParam String name, @RequestParam String email) {
        return memberService.createMember(name, email);
    }

    @GetMapping
    public List<Member> getMembers() {
        return memberService.getAllMembers();
    }

    @PatchMapping("/name")
    public Member updateName(@RequestParam Long id, @RequestParam String name) {
        return memberService.updateMemberName(id, name);
    }
}
