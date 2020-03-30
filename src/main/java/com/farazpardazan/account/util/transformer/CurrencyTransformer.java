package com.farazpardazan.account.util.transformer;

import com.farazpardazan.account.domain.CurrencyEntity;
import com.farazpardazan.shareddtos.account.dto.CurrencyDto;
import org.springframework.stereotype.Component;

@Component
public class CurrencyTransformer {

    public static CurrencyEntity transform (CurrencyDto dto){
        CurrencyEntity output = new CurrencyEntity();
        output.setId(dto.getId());
        output.setName(dto.getName());
        output.setRateAgainstGold(dto.getRateAgainstGold());
        return output;
    }

    public static CurrencyDto transform (CurrencyEntity entity){
        CurrencyDto output = new CurrencyDto();
        output.setId(entity.getId());
        output.setName(entity.getName());
        output.setRateAgainstGold(entity.getRateAgainstGold());
        return output;
    }
}
