package com.mshindelar.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private String id;
    private String principalId;
    private String username;
    private int discriminator;
    private String email;
    private String avatar;
    private LocalDateTime creationDate;
    private LocalDateTime lastLogin;
    @JsonIgnore
    private String accessToken;
    @JsonIgnore
    private String refreshToken;
}