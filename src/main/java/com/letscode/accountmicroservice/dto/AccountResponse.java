package com.letscode.accountmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.letscode.accountmicroservice.entities.Account;
import lombok.Data;



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
        response.setAccountType(accountRequest.getAccountType());
        return response;
    }


    public static AccountResponse toResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setAccountType(account.getAccountType());
        response.setAgency(account.getAgency());
        response.setAccountNumber(account.getAccountNumber());
        response.setName(account.getClient().getName());
        response.setCpf(account.getClient().getCpf());
        return response;
    }
}
