package com.sw.bank.controller;

import com.sw.bank.adapter.NBPExchangeRateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyAccountService {

    private final NBPExchangeRateClient nbpExchangeRateClient;

    @Autowired
    public CurrencyAccountService(NBPExchangeRateClient nbpExchangeRateClient) {
        this.nbpExchangeRateClient = nbpExchangeRateClient;
    }
}
