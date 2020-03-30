package com.farazpardazan.account.domain;

import com.farazpardazan.account.domain.account.AccountEntity;
import com.farazpardazan.shareddtos.account.enums.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Saeed Gholizadeh gholizade.saeed@yahoo.com
 */
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private TransactionType type;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;
    private String description;
    private LocalDateTime date ;
    private LocalDateTime receiveDate;
    private Integer referenceNo;
    private Boolean returned;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TransactionEntity source;
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;
    //etc...
    //for example Transactions can be many types with specific entity
    // account to account
    // cart to account and ...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDateTime receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Integer getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(Integer referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public TransactionEntity getSource() {
        return source;
    }

    public void setSource(TransactionEntity source) {
        this.source = source;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
