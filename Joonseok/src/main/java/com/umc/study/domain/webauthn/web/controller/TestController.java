package com.umc.study.domain.webauthn.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/passkey")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "/auth/index.html";
    }
}
