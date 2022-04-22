package com.letscode.accountmicroservice.services;

import com.letscode.accountmicroservice.clients.GetCPFInfoClient;
import com.letscode.accountmicroservice.dto.AccountRequest;
import com.letscode.accountmicroservice.dto.AccountResponse;
import com.letscode.accountmicroservice.dto.clients.CPFInfo;
import com.letscode.accountmicroservice.dto.clients.CPFStatus;
import com.letscode.accountmicroservice.entities.Account;
import com.letscode.accountmicroservice.entities.Client;
import com.letscode.accountmicroservice.jms.producer.CreateAccountProducer;
import com.letscode.accountmicroservice.repository.AccountRepository;
import com.letscode.accountmicroservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final GetCPFInfoClient getCPFInfoClient;
    private final CreateAccountProducer createAccountProducer;

    public AccountResponse create(AccountRequest accountRequest){
        // http get user
        Account account = new Account();
        account.setAccountType(accountRequest.getAccountType());
        account.setAgency(accountRequest.getAgency());

        Client client = new Client();
        client.setCpf(accountRequest.getCpf());
        client.setName(accountRequest.getName());
        // todo validar cliente
        account.setClient(client);

        final CPFInfo cpfInfo = getCPFInfoClient.execute(accountRequest.getCpf());

        if (getCPFInfoClient.execute(accountRequest.getCpf()).getCpfStatus().equals(CPFStatus.DISPONIVEL)) {
            String accountNumber = UUID.randomUUID().toString().replaceAll("[^\\d]","");
            account.setAccountNumber(accountNumber);
            clientRepository.save(client);
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
