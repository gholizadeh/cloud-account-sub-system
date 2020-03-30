package com.farazpardazan.account.exception;

public class AccountNotSupported extends RuntimeException {
    public AccountNotSupported() {
        super("Not Supported Account Type");
    }
}
