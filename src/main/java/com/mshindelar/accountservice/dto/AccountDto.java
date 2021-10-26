package com.mshindelar.accountservice.dto;

import lombok.Data;

@Data
public class AccountDto {
    private String username;
    private int discriminator;
    private String avatar;
}
