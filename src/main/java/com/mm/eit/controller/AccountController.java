package com.mm.eit.controller;

import com.mm.eit.model.Account;
import com.mm.eit.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(@RequestParam(required = false) String name) {
        try {
            List<Account> accounts = new ArrayList<Account>();

            if (name == null)
                accountRepository.findAll().forEach(accounts::add);
            else
                accountRepository.findByName(name).forEach(accounts::add);

            if (accounts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") long id) {
        Optional<Account> accountData = accountRepository.findById(id);

        if (accountData.isPresent()) {
            return new ResponseEntity<>(accountData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        try {
            Account _account = accountRepository
                    .save(Account.builder()
                            .name(account.getName())
                            .description(account.getDescription())
                            .build());
            return new ResponseEntity<>(_account, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
        Optional<Account> accountData = accountRepository.findById(id);

        if (accountData.isPresent()) {
            Account _account = accountData.get();
            _account.setName(account.getName());
            _account.setDescription(account.getDescription());
            _account.setBalance(account.getBalance());
            _account.setCurrency(account.getCurrency());
            _account.setRecords(account.getRecords());
            return new ResponseEntity<>(accountRepository.save(_account), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") long id) {
        try {
            accountRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/accounts")
    public ResponseEntity<HttpStatus> deleteAllAccounts() {
        try {
            accountRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
