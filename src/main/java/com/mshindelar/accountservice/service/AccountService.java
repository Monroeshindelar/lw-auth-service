package com.mshindelar.accountservice.service;

import com.mshindelar.accountservice.entity.Account;
import com.mshindelar.accountservice.exception.ResourceNotFoundException;
import com.mshindelar.accountservice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccount(String principalId) {
        return this.accountRepository.findByPrincipalId(principalId).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", principalId));
    }
}
