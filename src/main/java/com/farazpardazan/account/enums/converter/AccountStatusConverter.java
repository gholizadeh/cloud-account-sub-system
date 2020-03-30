package com.farazpardazan.account.enums.converter;

import com.farazpardazan.shareddtos.account.enums.AccountStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * @author Saeed Gholizadeh gholizade.saeed@yahoo.com
 */
@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<AccountStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AccountStatus status) {
        if (status == null) {
            return null;
        }
        return status.getCode();
    }

    @Override
    public AccountStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(AccountStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}