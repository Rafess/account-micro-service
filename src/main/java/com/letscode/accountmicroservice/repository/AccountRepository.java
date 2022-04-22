package com.letscode.accountmicroservice.repository;

import com.letscode.accountmicroservice.dto.AccountResponse;
import com.letscode.accountmicroservice.entities.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


}
