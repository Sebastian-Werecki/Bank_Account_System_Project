package com.sw.bank.controller;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CurrencyAccountChangeAmountAndCurrency {

    private BigDecimal balanceAmount;

    private String balanceCurrency;

}
