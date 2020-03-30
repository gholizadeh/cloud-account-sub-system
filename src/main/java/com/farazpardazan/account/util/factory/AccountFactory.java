package com.farazpardazan.account.util.factory;

import com.farazpardazan.account.domain.account.AccountEntity;
import com.farazpardazan.account.domain.account.deposit.DebtDepositAccountEntity;
import com.farazpardazan.account.domain.account.deposit.SavingDepositAccountEntity;
import com.farazpardazan.account.domain.account.investment.LongInvestmentAccountEntity;
import com.farazpardazan.account.domain.account.investment.ShortInvestmentAccountEntity;
import com.farazpardazan.account.exception.AccountNotSupported;
import com.farazpardazan.account.util.transformer.AccountTransformer;
import com.farazpardazan.shareddtos.account.dto.AccountDto;
import com.farazpardazan.shareddtos.account.dto.deposit.DebtDepositAccountDto;
import com.farazpardazan.shareddtos.account.dto.deposit.SavingDepositAccountDto;
import com.farazpardazan.shareddtos.account.dto.investment.LongInvestmentAccountDto;
import com.farazpardazan.shareddtos.account.dto.investment.ShortInvestmentAccountDto;

public class AccountFactory {
    public static AccountEntity toEntity(AccountDto dto) {
        if(dto instanceof SavingDepositAccountDto)
            return AccountTransformer.transform((SavingDepositAccountDto) dto);
        if(dto instanceof DebtDepositAccountDto)
            return AccountTransformer.transform((DebtDepositAccountDto) dto);
        if(dto instanceof ShortInvestmentAccountDto)
            return AccountTransformer.transform((ShortInvestmentAccountDto) dto);
        if(dto instanceof LongInvestmentAccountDto)
            return AccountTransformer.transform((LongInvestmentAccountDto) dto);
        else
            throw new AccountNotSupported();
    }

    public static AccountDto toDto(AccountEntity entity) {
        if(entity instanceof SavingDepositAccountEntity)
            return AccountTransformer.transform((SavingDepositAccountEntity) entity);
        if(entity instanceof DebtDepositAccountEntity)
            return AccountTransformer.transform((DebtDepositAccountEntity) entity);
        if(entity instanceof ShortInvestmentAccountEntity)
            return AccountTransformer.transform((ShortInvestmentAccountEntity) entity);
        if(entity instanceof LongInvestmentAccountEntity)
            return AccountTransformer.transform((LongInvestmentAccountEntity) entity);
        else
            throw new AccountNotSupported();
    }
}
