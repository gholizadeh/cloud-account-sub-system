package com.farazpardazan.account.api;

import com.farazpardazan.account.service.AccountService;
import com.farazpardazan.shareddtos.account.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Saeed Gholizadeh gholizade.saeed@yahoo.com
 */
@RestController
@RequestMapping("account")
public class AccountApi {

    @Autowired
    private AccountService service;

    @PostMapping
    public Optional<Long> createAccount(@RequestBody AccountDto account){
        return service.saveAccount(account);
    }

    @PutMapping()
    public AccountDto updateAccount(@RequestBody AccountDto account){
        return service.updateAccount(account);
    }
}
