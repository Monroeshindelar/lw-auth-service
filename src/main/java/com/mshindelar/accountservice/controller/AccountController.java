package com.mshindelar.accountservice.controller;

import com.mshindelar.accountservice.exception.ResourceNotFoundException;
import com.mshindelar.accountservice.repository.AccountRepository;
import com.mshindelar.accountservice.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

//    @GetMapping("me")
//    @PreAuthorize("hasRole('USER')")
//    public Account getCurrentUser(OAuth2AuthenticationToken token) {
//        //return accountRepository.findByPrincipalId((String) token.getPrincipal().getAttributes().get("id"));
//        return null;
//    }

    @GetMapping("me")
    public Account getCurrentUser(@RequestHeader("x-auth-header") String id) {
        return this.accountRepository.findByPrincipalId(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
    }
}
