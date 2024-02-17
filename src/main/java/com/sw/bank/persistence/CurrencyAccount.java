package com.sw.bank.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class CurrencyAccount {

    @Id
    private String accountId;

    private String ownerFirstName;

    private String ownerLastName;

    private BigDecimal balanceAmount;

    private String balanceCurrency;

    private Integer ownerAge;

}
