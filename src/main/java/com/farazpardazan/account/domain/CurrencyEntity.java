package com.farazpardazan.account.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Saeed Gholizadeh gholizade.saeed@yahoo.com
 */
@Entity
@Table(name = "currency")
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column ( name="rate_against_gold", precision = 6, scale = 4 )
    private BigDecimal rateAgainstGold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRateAgainstGold() {
        return rateAgainstGold;
    }

    public void setRateAgainstGold(BigDecimal rateAgainstGold) {
        this.rateAgainstGold = rateAgainstGold;
    }
}
