package com.sw.bank.controller;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateCurrencyAccount {

    private String ownerFirstName;

    private String ownerLastName;

    private BigDecimal balanceAmount;

    private String balanceCurrency;

    private Integer ownerAge;

}
