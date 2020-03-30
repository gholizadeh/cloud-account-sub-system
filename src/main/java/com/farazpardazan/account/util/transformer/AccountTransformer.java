package com.farazpardazan.account.util.transformer;

import com.farazpardazan.account.domain.account.AccountEntity;
import com.farazpardazan.account.domain.account.deposit.DebtDepositAccountEntity;
import com.farazpardazan.account.domain.account.deposit.SavingDepositAccountEntity;
import com.farazpardazan.account.domain.account.investment.LongInvestmentAccountEntity;
import com.farazpardazan.account.domain.account.investment.ShortInvestmentAccountEntity;
import com.farazpardazan.account.exception.AccountNotSupported;
import com.farazpardazan.account.util.factory.AccountFactory;
import com.farazpardazan.shareddtos.account.dto.AccountDto;
import com.farazpardazan.shareddtos.account.dto.deposit.DebtDepositAccountDto;
import com.farazpardazan.shareddtos.account.dto.deposit.SavingDepositAccountDto;
import com.farazpardazan.shareddtos.account.dto.investment.LongInvestmentAccountDto;
import com.farazpardazan.shareddtos.account.dto.investment.ShortInvestmentAccountDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountTransformer {

    public static AccountEntity transform(AccountDto accountDto) throws AccountNotSupported {
        return AccountFactory.toEntity(accountDto);
    }

    public static void generalEntityTransformation(AccountDto dto, AccountEntity entity){
        entity.setId(dto.getId());
        entity.setVersion(dto.getVersion());
        entity.setAccountNo(dto.getAccountNo());
        entity.setName(dto.getName());
        entity.setStartDate(dto.getStartDate());
        entity.setStatus(dto.getStatus());
        entity.setBranchId(dto.getBranch().getId());
        entity.setOpenerUserId(dto.getOpenerUser().getId());
        entity.setCurrency(CurrencyTransformer.transform(dto.getCurrency()));
        entity.setAccountOwners(
                dto.getAccountOwners().stream().map(item -> AccountOwnerTransformer.transform(item)).collect(Collectors.toSet())
        );
    }

    public static DebtDepositAccountEntity transform(DebtDepositAccountDto dto){
        DebtDepositAccountEntity output = new DebtDepositAccountEntity();
        //transform general info
        generalEntityTransformation(dto, output);
        //special params

        return output;
    }

    public static SavingDepositAccountEntity transform(SavingDepositAccountDto dto){
        SavingDepositAccountEntity output = new SavingDepositAccountEntity();
        //transform general info
        generalEntityTransformation(dto, output);
        //special params

        return output;
    }

    public static ShortInvestmentAccountEntity transform(ShortInvestmentAccountDto dto){
        ShortInvestmentAccountEntity output = new ShortInvestmentAccountEntity();
        //transform general info
        generalEntityTransformation(dto, output);
        //special params

        return output;
    }

    public static LongInvestmentAccountEntity transform(LongInvestmentAccountDto dto){
        LongInvestmentAccountEntity output = new LongInvestmentAccountEntity();
        //transform general info
        generalEntityTransformation(dto, output);
        //special params

        return output;
    }

    public static AccountDto transform(AccountEntity entity) throws AccountNotSupported {
        return AccountFactory.toDto(entity);
    }

    public static void generalDtoTransformation(AccountEntity entity, AccountDto dto){
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setAccountNo(entity.getAccountNo());
        dto.setName(entity.getName());
        dto.setStartDate(entity.getStartDate());
        dto.setStatus(entity.getStatus());
        //there should be another system call
        //dto.setBranch();
        //dto.setOpenerUser();
        dto.setCurrency(CurrencyTransformer.transform(entity.getCurrency()));
        dto.setAccountOwners(
                entity.getAccountOwners().stream().map(item -> AccountOwnerTransformer.transform(item)).collect(Collectors.toSet())
        );
    }

    public static DebtDepositAccountDto transform(DebtDepositAccountEntity entity){
        DebtDepositAccountDto output = new DebtDepositAccountDto();
        //transform general info
        generalDtoTransformation(entity, output);
        //special params

        return output;
    }

    public static SavingDepositAccountDto transform(SavingDepositAccountEntity entity){
        SavingDepositAccountDto output = new SavingDepositAccountDto();
        //transform general info
        generalDtoTransformation(entity, output);
        //special params

        return output;
    }

    public static ShortInvestmentAccountDto transform(ShortInvestmentAccountEntity entity){
        ShortInvestmentAccountDto output = new ShortInvestmentAccountDto();
        //transform general info
        generalDtoTransformation(entity, output);
        //special params

        return output;
    }

    public static LongInvestmentAccountDto transform(LongInvestmentAccountEntity entity){
        LongInvestmentAccountDto output = new LongInvestmentAccountDto();
        //transform general info
        generalDtoTransformation(entity, output);
        //special params

        return output;
    }
}
