package com.farazpardazan.account.repository;

import com.farazpardazan.account.domain.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
