package com.umc.study.global.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@RestController
@RequestMapping("/server")
public class ServerInfoController {

    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> getServerInfo() throws UnknownHostException {
        InetAddress host = InetAddress.getLocalHost();
        return ResponseEntity.ok(Map.of(
                "hostname", host.getHostName(),
                "ip", host.getHostAddress()
        ));
    }
}
