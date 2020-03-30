package com.farazpardazan.account.util.transformer;

import com.farazpardazan.account.domain.AccountOwnerEntity;
import com.farazpardazan.shareddtos.account.dto.AccountOwnerDto;
import org.springframework.stereotype.Component;

@Component
public class AccountOwnerTransformer {

    public static AccountOwnerEntity transform (AccountOwnerDto dto){
        AccountOwnerEntity output = new AccountOwnerEntity();
        output.setClientId(dto.getClient().getId());
        output.setSubscriptionRate(dto.getSubscriptionRate());
        return output;
    }

    public static AccountOwnerDto transform (AccountOwnerEntity dto){
        AccountOwnerDto output = new AccountOwnerDto();
        //there should be another system call
        //output.setClient();
        output.setSubscriptionRate(dto.getSubscriptionRate());
        return output;
    }
}
