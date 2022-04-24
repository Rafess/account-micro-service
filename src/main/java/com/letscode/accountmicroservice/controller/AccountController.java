package com.letscode.accountmicroservice.controller;

import com.letscode.accountmicroservice.dto.AccountRequest;
import com.letscode.accountmicroservice.dto.AccountResponse;
import com.letscode.accountmicroservice.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AccountResponse create(@RequestBody AccountRequest accountRequest) {

        return accountService.create(accountRequest);
    }
    @DeleteMapping("/delete/{accountNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable String accountNumber) {
        accountService.delete(accountNumber);
    }

    @GetMapping("{accountNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public AccountResponse getAccount(@PathVariable String accountNumber) {
        return accountService.getAccount(accountNumber);
    }

    @PutMapping("update/{accountNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public AccountResponse updateAccount(@PathVariable String accountNumber, @RequestBody AccountRequest accountRequest){
        return accountService.updateAccount(accountNumber, accountRequest);
    }
}
