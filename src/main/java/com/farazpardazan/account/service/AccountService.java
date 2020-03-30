package com.farazpardazan.account.service;

import com.farazpardazan.account.domain.TransactionEntity;
import com.farazpardazan.account.domain.account.AccountEntity;
import com.farazpardazan.account.exception.AccountNotFound;
import com.farazpardazan.account.exception.WrongAmount;
import com.farazpardazan.account.repository.AccountRepository;
import com.farazpardazan.account.util.transformer.AccountTransformer;
import com.farazpardazan.shareddtos.account.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountTransformer transformer;

    @Autowired
    private AccountRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private CurrencyService currencyService;

    public Optional<Long> saveAccount(AccountDto accountDto){
        AccountEntity entity = transformer.transform(accountDto);
        return Optional.ofNullable(repository.save(entity).getId());
    }

    public AccountEntity getAccount(Long accountId){
        return repository.findById(accountId).orElseThrow(() -> new AccountNotFound());
    }

    //there also can be a less detailed account dto or a rule to prevent updating all details like accountNo\
    @Transactional
    public AccountDto updateAccount(AccountDto accountDto){
        //to check if account exist and prevent updating current amount through update service
        BigDecimal currentAmount = getAccount(accountDto.getId()).getCurrentAmount();
        AccountEntity entity = transformer.transform(accountDto);
        entity.setCurrentAmount(currentAmount);
        entityManager.merge(entity);
        entityManager.flush();

        return AccountTransformer.transform(entity);
    }

    public BigDecimal makeTransaction(TransactionEntity tr){
        AccountEntity account = tr.getAccount();

        if(tr.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new WrongAmount();
        }
        //currency check
        BigDecimal amount;
        if(tr.getCurrency().getId().equals(account.getCurrency().getId()))
            amount = tr.getAmount();
        else
            amount = currencyService.convertAmount(tr.getCurrency(), account.getCurrency(), tr.getAmount());

        switch (tr.getType()){
            case DEBIT: account.withdraw(amount); break;
            case DEPOSIT: account.deposit(amount); break;
        }

        return account.getCurrentAmount();
    }
}
