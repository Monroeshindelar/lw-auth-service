package com.tm.accountservice.repository;

import com.tm.accountservice.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long> {
    Account getAccountById(Long accountId);
}
