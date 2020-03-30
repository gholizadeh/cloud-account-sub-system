package com.farazpardazan.account.service;

import com.farazpardazan.account.domain.TransactionEntity;
import com.farazpardazan.account.domain.account.AccountEntity;
import com.farazpardazan.account.repository.TransactionRepository;
import com.farazpardazan.account.util.transformer.TransactionTransformer;
import com.farazpardazan.shareddtos.account.dto.TransactionDto;
import com.farazpardazan.shareddtos.account.dto.TransactionInfoDto;
import com.farazpardazan.shareddtos.account.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionTransformer transformer;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public Optional<Long> makeTransaction(Long accountId, TransactionDto dto){
        AccountEntity account = accountService.getAccount(accountId);
        TransactionEntity tr = transformer.transform(dto);
        tr.setAccount(account);
        return makeTransaction(tr);
    }

    private Optional<Long> makeTransaction(TransactionEntity entity){
        accountService.makeTransaction(entity);
        return Optional.of(repository.save(entity).getId());
    }

    //as this service is minimal we just consider a transfer as two transaction.
    @Transactional
    public void transfer(Long sourceAccountId, Long destinationAccountId, TransactionInfoDto trInfo){
        AccountEntity sourceAccount = accountService.getAccount(sourceAccountId);
        TransactionEntity firstTr = transformer.transformTrInfoToEntity(trInfo, sourceAccount, TransactionType.DEBIT);
        accountService.makeTransaction(firstTr);
        repository.save(firstTr);

        AccountEntity destinationAccount = accountService.getAccount(destinationAccountId);
        TransactionEntity secondTr = transformer.transformTrInfoToEntity(trInfo, destinationAccount, TransactionType.DEPOSIT);
        accountService.makeTransaction(secondTr);
        repository.save(secondTr);
    }
}
