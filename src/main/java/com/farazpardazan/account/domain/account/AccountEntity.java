package com.farazpardazan.account.domain.account;

import com.farazpardazan.account.domain.AccountOwnerEntity;
import com.farazpardazan.account.domain.CurrencyEntity;
import com.farazpardazan.account.exception.NotEnoughRemain;
import com.farazpardazan.shareddtos.account.enums.AccountStatus;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Saeed Gholizadeh gholizade.saeed@yahoo.com
 */
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class AccountEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Version
    private Integer version;
    private Long accountNo;
    private String name;
    private LocalDateTime startDate;
    @ColumnDefault("'0.0'")
    @Column(insertable=false, nullable = false)
    private BigDecimal currentAmount;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;
    private AccountStatus status;
    private Long branchId;
    private Long openerUserId;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountOwnerEntity> accountOwners;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public synchronized BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public synchronized void withdraw(BigDecimal amount) {
        if(currentAmount.compareTo(amount) >= 0){
            this.currentAmount = currentAmount.subtract(amount);
        }else{
            throw new NotEnoughRemain();
        }
    }

    public synchronized void deposit(BigDecimal amount) {
        this.currentAmount = this.currentAmount.add(amount);
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getOpenerUserId() {
        return openerUserId;
    }

    public void setOpenerUserId(Long openerUserId) {
        this.openerUserId = openerUserId;
    }

    public Set<AccountOwnerEntity> getAccountOwners() {
        return accountOwners;
    }

    public void setAccountOwners(Set<AccountOwnerEntity> accountOwners) {
        this.accountOwners = accountOwners;
    }
}
