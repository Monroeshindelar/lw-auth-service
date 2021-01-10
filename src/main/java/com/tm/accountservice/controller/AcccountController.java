package com.tm.accountservice.controller;

import com.tm.accountservice.entity.Account;
import com.tm.accountservice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AcccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable("id") Long accountId) {
        return accountRepository.getAccountById(accountId);
    }

    @PostMapping("/create")
    public void createAccount(@RequestBody Account account) {
        Account insertedAccount = accountRepository.insert(account);
    }
}
