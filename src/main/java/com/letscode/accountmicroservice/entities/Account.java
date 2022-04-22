package com.letscode.accountmicroservice.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String agency;
    @Column(name = "account_number")
    private String accountNumber;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "account_type")
    private AccountType accountType;
}
