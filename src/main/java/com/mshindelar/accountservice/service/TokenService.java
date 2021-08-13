package com.mshindelar.accountservice.service;

import com.mshindelar.accountservice.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TokenService {

    @Autowired
    private TokenProvider tokenProvider;

    public String validateToken(String token) {
        //return this.tokenProvider.validateToken(token);
        if(!this.tokenProvider.validateToken(token)) throw new RuntimeException("Authentiction error.");

        return this.tokenProvider.getUserIdFromToken(token);
    }
}
