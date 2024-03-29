package com.sw.bank.controller;

public class CurrencyAccountNotFound extends RuntimeException{

    public CurrencyAccountNotFound(String message) {
        super (message);
    }

}
