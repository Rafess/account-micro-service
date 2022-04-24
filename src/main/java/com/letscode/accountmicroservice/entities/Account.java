package com.letscode.accountmicroservice.entities;

import com.letscode.accountmicroservice.entities.client.Client;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "agency")
    private String agency;
    @Column(name = "account_number")
    private String accountNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "account_type")
    private AccountType accountType;

    private String password;
}
