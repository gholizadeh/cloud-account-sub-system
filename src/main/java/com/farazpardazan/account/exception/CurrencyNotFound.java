package com.farazpardazan.account.exception;

public class CurrencyNotFound extends RuntimeException {
    public CurrencyNotFound() {
        super("Currency Not Found");
    }
}
