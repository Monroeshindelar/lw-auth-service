package com.mshindelar.accountservice.auth;

import com.mshindelar.accountservice.entity.Account;
import com.mshindelar.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class DiscordOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User user = super.loadUser(userRequest);

        Optional<Account> accountOptional = accountRepository.findByPrincipalId(user.getAttribute("id"));

        Account account;
        if(accountOptional.isEmpty()) {
            account = new Account();
            account.setPrincipalId(user.getAttribute("id"));
            account.setUsername(user.getAttribute("username"));
            account.setDiscriminator(Integer.parseInt(Objects.requireNonNull(user.getAttribute("discriminator"))));
            account.setEmail(user.getAttribute("email"));
            account.setCreationDate(LocalDateTime.now());
        } else {
            account = accountOptional.get();
        }

        account.setAvatar(user.getAttribute("avatar"));
        account.setLastLogin(LocalDateTime.now());

        accountRepository.save(account);

        return user;
    }
}
