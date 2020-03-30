package com.farazpardazan.account.enums.converter;

import com.farazpardazan.shareddtos.account.enums.TransactionType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * @author Saeed Gholizadeh gholizade.saeed@yahoo.com
 */
@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TransactionType tr) {
        if (tr == null) {
            return null;
        }
        return tr.getCode();
    }

    @Override
    public TransactionType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(TransactionType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}