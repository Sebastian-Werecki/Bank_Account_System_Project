package com.sw.bank.adapter;

import java.math.BigDecimal;
import java.util.List;

public record NBPSingleExchangeRateResponse(List<Rate> rates) {
    record Rate(BigDecimal mid) {}
}
