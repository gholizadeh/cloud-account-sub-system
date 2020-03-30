package com.farazpardazan.account.domain;

import com.farazpardazan.account.domain.account.AccountEntity;

import javax.persistence.*;

/**
 * @author Saeed Gholizadeh gholizade.saeed@yahoo.com
 */
@Entity
public class AccountOwnerEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;
    private Long clientId;
    //سهم الشراکه
    private Short subscriptionRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Short getSubscriptionRate() {
        return subscriptionRate;
    }

    public void setSubscriptionRate(Short subscriptionRate) {
        this.subscriptionRate = subscriptionRate;
    }
}
