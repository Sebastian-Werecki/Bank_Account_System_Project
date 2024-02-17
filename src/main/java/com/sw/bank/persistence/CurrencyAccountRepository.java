package com.sw.bank.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyAccountRepository extends JpaRepository<CurrencyAccount, String> {
}
