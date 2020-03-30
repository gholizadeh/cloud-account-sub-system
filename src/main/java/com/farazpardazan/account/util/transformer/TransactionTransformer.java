package com.farazpardazan.account.util.transformer;

import com.farazpardazan.account.domain.TransactionEntity;
import com.farazpardazan.account.domain.account.AccountEntity;
import com.farazpardazan.account.exception.AccountNotSupported;
import com.farazpardazan.shareddtos.account.dto.AccountDto;
import com.farazpardazan.shareddtos.account.dto.TransactionDto;
import com.farazpardazan.shareddtos.account.dto.TransactionInfoDto;
import com.farazpardazan.shareddtos.account.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class TransactionTransformer {
    public static final int FirstDeep = 1;

    public static TransactionEntity transform(TransactionDto tr){
        return transform(tr, FirstDeep);
    }

    public static TransactionEntity transform(TransactionDto tr, int deep){
        TransactionEntity output = new TransactionEntity();
        output.setType(tr.getType());
        output.setAmount(tr.getAmount());
        output.setDate(tr.getDate());
        //can be an audit field in db instead
        output.setReceiveDate(LocalDateTime.now());
        output.setDescription(tr.getDescription());
        output.setReferenceNo(tr.getReferenceNo());
        output.setReturned(tr.getReturned());
        if(Objects.nonNull(tr.getAccount()))
            output.setAccount(AccountTransformer.transform(tr.getAccount()));
        if(Objects.nonNull(tr.getCurrency()))
            output.setCurrency(CurrencyTransformer.transform(tr.getCurrency()));
        //to prevent loop
        deep--;
        if(deep > 0 && Objects.nonNull(tr.getSource()))
            output.setSource(TransactionTransformer.transform(tr.getSource(), deep));

        return output;
    }

    public static TransactionEntity transformTrInfoToEntity(TransactionInfoDto dto, AccountEntity account, TransactionType type) {
        TransactionEntity tr = new TransactionEntity();
        tr.setAccount(account);
        if(Objects.nonNull(dto.getCurrency()))
            tr.setCurrency(CurrencyTransformer.transform(dto.getCurrency()));
        tr.setType(type);
        if(Objects.nonNull(dto.getSource()))
            tr.setSource(transform(dto.getSource()));
        tr.setReturned(dto.getReturned());
        tr.setReferenceNo(dto.getReferenceNo());
        tr.setDescription(dto.getDescription());
        tr.setDate(dto.getDate());
        tr.setReceiveDate(LocalDateTime.now());
        tr.setAmount(dto.getAmount());
        if(Objects.nonNull(dto.getUser()))
            tr.setUserId(dto.getUser().getId());

        return tr;
    }
}
