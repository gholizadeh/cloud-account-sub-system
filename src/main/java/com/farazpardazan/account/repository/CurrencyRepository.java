package com.farazpardazan.account.repository;

import com.farazpardazan.account.domain.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {
}
