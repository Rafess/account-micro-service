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
}
