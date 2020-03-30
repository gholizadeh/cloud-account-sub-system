package com.farazpardazan.account.service;

import com.farazpardazan.account.domain.CurrencyEntity;
import com.farazpardazan.account.exception.CurrencyNotFound;
import com.farazpardazan.account.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository repository;

    public BigDecimal convertAmount(CurrencyEntity from, CurrencyEntity to, BigDecimal amount){
        Optional<CurrencyEntity> fromCurrency = repository.findById(from.getId());
        Optional<CurrencyEntity> toCurrency = repository.findById(to.getId());
        if(fromCurrency.isPresent() && toCurrency.isPresent()){
            return amount.multiply(fromCurrency.get().getRateAgainstGold()).divide(toCurrency.get().getRateAgainstGold());
        }else{
            throw new CurrencyNotFound();
        }
    }
}
