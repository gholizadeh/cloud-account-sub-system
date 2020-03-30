package com.farazpardazan.account.exception;

public class WrongAmount extends RuntimeException {
    public WrongAmount() {
        super("Amount is Wrong");
    }
}
