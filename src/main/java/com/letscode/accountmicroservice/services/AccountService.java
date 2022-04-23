package com.letscode.accountmicroservice.services;

import com.letscode.accountmicroservice.clients.GetCPFInfoClient;
import com.letscode.accountmicroservice.clients.GetClientInfoClient;
import com.letscode.accountmicroservice.dto.AccountRequest;
import com.letscode.accountmicroservice.dto.AccountResponse;
import com.letscode.accountmicroservice.dto.clients.CPFInfo;
import com.letscode.accountmicroservice.dto.clients.CPFStatus;
import com.letscode.accountmicroservice.entities.Account;
import com.letscode.accountmicroservice.entities.client.Client;
import com.letscode.accountmicroservice.jms.producer.CreateAccountProducer;
import com.letscode.accountmicroservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final GetCPFInfoClient getCPFInfoClient;
    private final CreateAccountProducer createAccountProducer;
    private final GetClientInfoClient getClientInfo;

    public AccountResponse create(AccountRequest accountRequest){
        // http get user
        Account account = new Account();
        account.setAccountType(accountRequest.getAccountType());
        account.setAgency(accountRequest.getAgency());

        final CPFInfo cpfInfo = getCPFInfoClient.execute(accountRequest.getCpf());
        final Client client = getClientInfo.execute(accountRequest.getCpf());
        account.setClient(client);
        if (getCPFInfoClient.execute(accountRequest.getCpf()).getCpfStatus().equals(CPFStatus.DISPONIVEL)) {
            String accountNumber = UUID.randomUUID().toString().replaceAll("[^\\d]","");
            account.setAccountNumber(accountNumber);
            accountRepository.save(account);

           return AccountResponse.fromRequest(accountRequest, account.getAccountNumber());

        } else if (cpfInfo.getCpfStatus().equals(CPFStatus.NAOCADASTRADO)) {
            com.letscode.accountmicroservice.dto.jms.Account newAccount = new com.letscode.accountmicroservice.dto.jms.Account();
            newAccount.setCpf(accountRequest.getCpf());
            newAccount.setName(accountRequest.getName());
            createAccountProducer.send(newAccount);

    }

        return null;
    }
}
