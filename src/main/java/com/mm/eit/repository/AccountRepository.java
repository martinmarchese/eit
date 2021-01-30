package com.mm.eit.repository;

import java.util.List;

import com.mm.eit.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByName(String name);
}