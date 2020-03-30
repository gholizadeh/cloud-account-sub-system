package com.farazpardazan.account.exception;

public class AccountNotFound extends RuntimeException {
    public AccountNotFound() {
        super("Account Id Not Found");
    }
}
