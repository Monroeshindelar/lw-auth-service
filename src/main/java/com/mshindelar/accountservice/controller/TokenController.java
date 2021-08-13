package com.mshindelar.accountservice.controller;

import com.mshindelar.accountservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("validate")
    public String validateToken(@RequestParam String token) {
        return this.tokenService.validateToken(token);
    }
}
