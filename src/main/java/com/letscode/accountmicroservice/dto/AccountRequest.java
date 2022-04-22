package com.letscode.accountmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.letscode.accountmicroservice.entities.AccountType;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class AccountRequest {

    private String cpf;
    private String name;
    private String password;
    private String agency;
    @JsonProperty("tipo-conta")
    private AccountType accountType;
}
