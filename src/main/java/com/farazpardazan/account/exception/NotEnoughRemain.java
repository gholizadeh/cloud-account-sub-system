package com.farazpardazan.account.exception;

public class NotEnoughRemain extends RuntimeException {
    public NotEnoughRemain() {
        super("Account has not enough remain");
    }
}
