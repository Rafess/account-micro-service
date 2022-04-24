package com.letscode.accountmicroservice.services;
import com.letscode.accountmicroservice.clients.GetClientInfoClient;
import com.letscode.accountmicroservice.dto.AccountRequest;
import com.letscode.accountmicroservice.dto.AccountResponse;
import com.letscode.accountmicroservice.entities.Account;
import com.letscode.accountmicroservice.entities.client.Client;
import com.letscode.accountmicroservice.exceptions.AccountNotFoundException;
import com.letscode.accountmicroservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final GetClientInfoClient getClientInfo;

    public AccountResponse create(AccountRequest accountRequest){
        Account account = new Account();
        account.setAccountType(accountRequest.getAccountType());
        account.setAgency(accountRequest.getAgency());
        final Client client = getClientInfo.execute(accountRequest.getCpf());
        account.setClient(client);
        String accountNumber = UUID.randomUUID().toString().replaceAll("[^\\d]","");
        account.setAccountNumber(accountNumber);
        accountRepository.save(account);

        return AccountResponse.fromRequest(accountRequest, account.getAccountNumber());
    }
    public AccountResponse getAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(AccountNotFoundException::new);
        return AccountResponse.toResponse(account);
    }
    public void delete(String accountNumber) {
       Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(AccountNotFoundException::new);
        accountRepository.delete(account);
    }

    public AccountResponse updateAccount(String accountNumber, AccountRequest accountRequest) {
        Account account =  accountRepository.findByAccountNumber(accountNumber).orElseThrow(AccountNotFoundException::new);
        account.setAccountType(accountRequest.getAccountType());
        account.setAgency(accountRequest.getAgency());
        account.setPassword(accountRequest.getPassword());
        accountRepository.save(account);
        return AccountResponse.toResponse(account);
    }
}
