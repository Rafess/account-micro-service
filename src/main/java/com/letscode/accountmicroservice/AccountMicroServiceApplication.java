package com.letscode.accountmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AccountMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountMicroServiceApplication.class, args);
    }

}
