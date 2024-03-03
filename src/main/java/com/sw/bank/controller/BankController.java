package com.sw.bank.controller;

import com.sw.bank.adapter.NBPExchangeRateClient;
import com.sw.bank.adapter.NBPSingleExchangeRateResponse;
import com.sw.bank.persistence.CurrencyAccount;
import com.sw.bank.persistence.CurrencyAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
public class BankController {

    private final CurrencyAccountRepository currencyAccountRepository;

    private final NBPExchangeRateClient nbpExchangeRateClient;

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
    public ResponseEntity<CurrencyAccount> createCurrencyAccount(@RequestBody CreateCurrencyAccount createCurrencyAccount) {
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

    @PatchMapping("/api/v1/currency-accounts/change-amount-and-currency/{accountId}")
    public ResponseEntity<CurrencyAccount> changeCurrencyAccount(@PathVariable("accountId") String accountId,
                                                                 @RequestBody CurrencyAccountChangeAmountAndCurrency currencyAccountChangeAmountAndCurrency) {
        var currencyAccount = currencyAccountRepository.findById(accountId)
                .orElseThrow(()->new CurrencyAccountNotFound("Account with this Id does not exist."));

        currencyAccount.setBalanceCurrency(currencyAccountChangeAmountAndCurrency.getBalanceCurrency());
        currencyAccount.setBalanceAmount(currencyAccountChangeAmountAndCurrency.getBalanceAmount());

        currencyAccountRepository.save(currencyAccount);
        return ResponseEntity.ok(currencyAccount);
    }

    @GetMapping("/api/v1/currency-accounts/check-usd-rate")
    public ResponseEntity<NBPSingleExchangeRateResponse> checkUSDRate() {

        var currencyAccount = nbpExchangeRateClient.getRate("A", "USD");

        return ResponseEntity.ok(currencyAccount);
    }

}
