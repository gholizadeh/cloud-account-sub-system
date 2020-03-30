package com.farazpardazan.account.api;

import com.farazpardazan.account.service.TransactionService;
import com.farazpardazan.shareddtos.account.dto.TransactionDto;
import com.farazpardazan.shareddtos.account.dto.TransactionInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Saeed Gholizadeh gholizade.saeed@yahoo.com
 */
@RestController
@RequestMapping("transaction")
public class TransactionApi {

    @Autowired
    private TransactionService service;

    @PostMapping("/{accountId}")
    public Optional<Long> makeTransaction(@PathVariable Long accountId, @RequestBody TransactionDto tr){
        return service.makeTransaction(accountId, tr);
    }

    @PostMapping("/account-transfer/{sourceAccountId}/{destinationAccountId}")
    public void makeTransaction(@PathVariable Long sourceAccountId, @PathVariable Long destinationAccountId, @RequestBody TransactionInfoDto trInfo){
        service.transfer(sourceAccountId, destinationAccountId, trInfo);
    }
}
