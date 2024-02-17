package com.sw.bank.controller;

import com.sw.bank.persistence.CurrencyAccount;
import com.sw.bank.persistence.CurrencyAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class BankController {

    private final CurrencyAccountRepository currencyAccountRepository;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello there :)";
    }

    @GetMapping("/api/v1/currency-accounts/{accountId}")
    public ResponseEntity<CurrencyAccount> findCurrencyAccount(@PathVariable("accountId") String accountId) {
        return currencyAccountRepository.findById(accountId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/v1/currency-accounts")
    public ResponseEntity<CurrencyAccount> createCurrencyAccount(@RequestBody CreateCurrencyAccount createCurrencyAccount){
        var currencyAccount = CurrencyAccount.builder()
                .accountId(UUID.randomUUID().toString())
                .balanceCurrency(createCurrencyAccount.getBalanceCurrency())
                .balanceAmount(createCurrencyAccount.getBalanceAmount())
                .ownerFirstName(createCurrencyAccount.getOwnerFirstName())
                .ownerLastName(createCurrencyAccount.getOwnerLastName())
                .ownerAge(createCurrencyAccount.getOwnerAge()).build();
        currencyAccountRepository.save(currencyAccount);
        return ResponseEntity.ok(currencyAccount);
    }

}
