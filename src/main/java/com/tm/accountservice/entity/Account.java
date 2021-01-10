package com.tm.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private long id;
    private String email;
}
