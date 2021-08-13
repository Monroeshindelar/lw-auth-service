package com.mshindelar.accountservice.service;

import com.mshindelar.accountservice.entity.Account;
import com.mshindelar.accountservice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountByPrincipalId(String id) {
        //return this.accountRepository.findByPrincipalId(id);
        return null;
    }
}
