package com.mshindelar.accountservice.controller;

import com.mshindelar.accountservice.dto.AccountDto;
import com.mshindelar.accountservice.entity.Account;
import com.mshindelar.accountservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    private AccountDto convertToDto(Account account) { return modelMapper.map(account, AccountDto.class); }

    @GetMapping("me")
    public Account getCurrentAuthenticatedUser(@RequestHeader("x-auth-header") String principalId) {
        return this.accountService.getAccount(principalId);
    }

    @GetMapping("{principalId}")
    public AccountDto getAccount(@PathVariable("principalId") String principalId) {
        return convertToDto(this.accountService.getAccount(principalId));
    }
}
