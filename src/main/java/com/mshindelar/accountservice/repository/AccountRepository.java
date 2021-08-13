package com.mshindelar.accountservice.repository;

import com.mshindelar.accountservice.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByPrincipalId(String principalId);

    Optional<Account> findByEmail(String email);

    Boolean existsByPrincipalId(String principalId);

    Boolean existsByEmail(String email);


}
