package com.letscode.accountmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class AccountResponse extends AccountRequest {

    @JsonProperty("number")
    private String accountNumber;

    public static AccountResponse fromRequest(AccountRequest accountRequest, String accountNumber) {
        AccountResponse response = new AccountResponse();
        response.setAccountNumber(accountNumber);
        response.setAgency(accountRequest.getAgency());
        response.setCpf(accountRequest.getCpf());
        response.setName(accountRequest.getName());
        return response;
    }
}