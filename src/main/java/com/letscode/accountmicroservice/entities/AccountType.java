package com.letscode.accountmicroservice.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum AccountType {
    POUPANCA("Poupança"), CONTA_CORRENTE("Conta Corrente"), SALARIO("Salário");
    private String label;
}
